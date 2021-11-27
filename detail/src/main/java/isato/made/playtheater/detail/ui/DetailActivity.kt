package isato.made.playtheater.detail.ui

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.presentation.model.MovieDetail
import isato.made.playtheater.core.ui.adapter.GenreAdapter
import isato.made.playtheater.core.util.ext.loadImage
import isato.made.playtheater.core.util.ext.loadTransformImage
import isato.made.playtheater.core.util.ext.showSnackbarNotice
import isato.made.playtheater.detail.R
import isato.made.playtheater.detail.databinding.ActivityDetailBinding
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import timber.log.Timber

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE_ID = "extra_movie_id"
        const val EXTRA_MOVIE_TITLE = "extra_movie_title"
    }

    private lateinit var binding: ActivityDetailBinding
    private val headerBinding get() = binding.contentHeader
    private val detailBinding get() = binding.contentDetail

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var genreAdapter: GenreAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { onBackPressed() }

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE_ID)
            val movieTitle = extras.getString(EXTRA_MOVIE_TITLE)

            binding.collapsingToolbar.title = movieTitle

            if (movieId != null) {
                detailViewModel.getMovieById(movieId)

                genreAdapter = GenreAdapter()
                binding.contentDetail.rvGenre.apply {
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = genreAdapter
                }

                detailViewModel.movieDetail.observe(this) { detail ->
                    Timber.d("Detail Result: $detail")
                    when (detail) {
                        is Resource.Success -> {
                            showLoading(false)
                            showMovieDetail(detail.data)
                        }
                        is Resource.Loading -> showLoading(true)
                        is Resource.Error -> {
                            binding.root.showSnackbarNotice(
                                detail.message ?: getString(R.string.error)
                            )
                        }
                    }
                }
            }
        }
    }

    private fun showMovieDetail(movieDetail: MovieDetail?) {
        movieDetail?.backdropPath?.let { path ->
            binding.ivBackdrop.loadTransformImage(
                path,
                ColorFilterTransformation(Color.argb(100, 0, 128, 255))
            )
        }
        movieDetail?.posterPath?.let { headerBinding.ivPoster.loadImage(it) }
        headerBinding.tvStatus.text = movieDetail?.status ?: "-"
        headerBinding.tvTagline.text = movieDetail?.tagline
        headerBinding.tvDate.text = movieDetail?.releaseDate ?: "-"
        genreAdapter.submitList(movieDetail?.genres)
        detailBinding.tvOverview.text = movieDetail?.overview ?: "-"
        movieDetail?.isFavorite?.let { setFavorite(it) }
        binding.fabFavorite.setOnClickListener {
            movieDetail?.isFavorite?.let { favorite ->
                detailViewModel.setFavoriteMovie()
                val newState = !favorite
                setFavorite(newState)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.appbarDetail.setBackgroundColor(
            if (isLoading) ContextCompat.getColor(
                applicationContext,
                R.color.grey
            ) else ContextCompat.getColor(applicationContext, R.color.dark_blue)
        )
        binding.ivBackdrop.visibility = if (isLoading) View.GONE else View.VISIBLE
        headerBinding.root.visibility = if (isLoading) View.GONE else View.VISIBLE
        binding.content.visibility = if (isLoading) View.GONE else View.VISIBLE
        binding.fabFavorite.visibility = if (isLoading) View.GONE else View.VISIBLE

        binding.contentDetailHeaderPlaceholder.root.visibility =
            if (isLoading) View.VISIBLE else View.GONE
        binding.contentDetailPlaceholder.root.visibility =
            if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setFavorite(isFavorite: Boolean) {
        binding.fabFavorite.setImageResource(
            if (isFavorite) {
                R.drawable.ic_favorite_baseline_white_24
            } else {
                R.drawable.ic_favorite_outline_white_24
            }
        )
    }
}
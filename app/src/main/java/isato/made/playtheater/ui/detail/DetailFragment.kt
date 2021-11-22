package isato.made.playtheater.ui.detail

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import isato.made.playtheater.R
import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.util.ext.loadImage
import isato.made.playtheater.core.util.ext.loadTransformImage
import isato.made.playtheater.core.util.ext.showSnackbarNotice
import isato.made.playtheater.databinding.FragmentDetailBinding
import isato.made.playtheater.model.MovieDetail
import isato.made.playtheater.ui.adapter.GenreAdapter
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import timber.log.Timber

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding
    private val headerBinding get() = binding?.contentHeader
    private val detailBinding get() = binding?.contentDetail

    private val args: DetailFragmentArgs by navArgs()

    private val detailViewModel: DetailViewModel by viewModels()
    private lateinit var genreAdapter: GenreAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailBinding.inflate(inflater, container, false)
        _binding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            detailViewModel.getMovieById(args.movieId)

            genreAdapter = GenreAdapter()
            binding?.contentDetail?.rvGenre?.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = genreAdapter
            }

            detailViewModel.movieDetail.observe(viewLifecycleOwner) { detail ->
                Timber.d("Detail Result: $detail")
                when (detail) {
                    is Resource.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        showMovieDetail(detail.data)
                    }
                    is Resource.Loading -> binding?.progressBar?.visibility = View.VISIBLE
                    is Resource.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        binding?.root?.showSnackbarNotice(
                            detail.message ?: getString(R.string.error)
                        )
                    }
                }
            }
        }
    }

    private fun showMovieDetail(movieDetail: MovieDetail?) {
        binding?.collapsingToolbar?.title = args.movieTitle
        binding?.ivBackdrop?.visibility = View.VISIBLE
        headerBinding?.contentHeader?.visibility = View.VISIBLE
        binding?.content?.visibility = View.VISIBLE
        binding?.fabFavorite?.visibility = View.VISIBLE

        movieDetail?.backdropPath?.let { path ->
            binding?.ivBackdrop?.loadTransformImage(
                path,
                ColorFilterTransformation(Color.argb(100, 0, 128, 255))
            )
        }
        movieDetail?.posterPath?.let { headerBinding?.ivPoster?.loadImage(it) }
        headerBinding?.tvStatus?.text = movieDetail?.status ?: "-"
        headerBinding?.tvTagline?.text = movieDetail?.tagline
        headerBinding?.tvDate?.text = movieDetail?.releaseDate ?: "-"
        genreAdapter.submitList(movieDetail?.genres)
        detailBinding?.tvOverview?.text = movieDetail?.overview ?: "-"
        movieDetail?.isFavorite?.let { setFavorite(it) }
    }

    private fun setFavorite(isFavorite: Boolean) {
        binding?.fabFavorite?.setImageResource(
            if (isFavorite) {
                R.drawable.ic_favorite_baseline_white_24
            } else {
                R.drawable.ic_favorite_outline_white_24
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
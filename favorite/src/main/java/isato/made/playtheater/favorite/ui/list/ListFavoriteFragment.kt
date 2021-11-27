package isato.made.playtheater.favorite.ui.list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaelmarhic.quadrant.QuadrantConstants
import dagger.hilt.android.EntryPointAccessors
import isato.made.playtheater.core.ui.adapter.MovieAdapter
import isato.made.playtheater.detail.ui.DetailActivity
import isato.made.playtheater.di.FavoriteModuleDependencies
import isato.made.playtheater.favorite.databinding.FragmentListFavoriteBinding
import isato.made.playtheater.favorite.di.DaggerFavoriteComponent
import javax.inject.Inject

class ListFavoriteFragment : Fragment() {

    private var _binding: FragmentListFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var favoriteViewModel: ListFavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val favoriteModuleDependencies = EntryPointAccessors.fromApplication(
            requireActivity().applicationContext,
            FavoriteModuleDependencies::class.java
        )
        DaggerFavoriteComponent.factory().create(favoriteModuleDependencies).inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { movieId, movieTitle ->
                navigateToDetailActivity(movieId, movieTitle)
            }

            binding.rvFavorite.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
            }

            favoriteViewModel.favoriteMovies.observe(viewLifecycleOwner) { favoriteMovies ->
                movieAdapter.submitList(favoriteMovies)
            }
        }
    }

    private fun navigateToDetailActivity(movieId: String, movieTitle: String) {
        Intent().apply {
            putExtra(DetailActivity.EXTRA_MOVIE_ID, movieId)
            putExtra(DetailActivity.EXTRA_MOVIE_TITLE, movieTitle)
            setClassName(requireContext(), QuadrantConstants.DETAIL_ACTIVITY)
        }.also {
            startActivity(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
package isato.made.playtheater.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gaelmarhic.quadrant.QuadrantConstants
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.ui.adapter.MovieAdapter
import isato.made.playtheater.databinding.FragmentHomeBinding
import isato.made.playtheater.detail.ui.DetailActivity

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var homeBinding: FragmentHomeBinding? = null

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { movieId, movieTitle ->
                navigateToDetailActivity(movieId, movieTitle)
            }

            homeBinding?.rvMovie?.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
            }

            homeViewModel.movies.observe(viewLifecycleOwner) { movies ->
                when (movies) {
                    is Resource.Success -> {
                        homeBinding?.progressBar?.visibility = View.GONE
                        movieAdapter.submitList(movies.data)
                    }
                    is Resource.Loading -> {
                        homeBinding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        homeBinding?.progressBar?.visibility = View.GONE
                        movies.message?.let {
                            Snackbar.make(view,
                                it, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
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
        homeBinding = null
    }

}
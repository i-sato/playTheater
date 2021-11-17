package isato.made.playtheater.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.ui.MovieAdapter
import isato.made.playtheater.databinding.FragmentHomeBinding
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val movieAdapter = MovieAdapter()
            movieAdapter.onItemClick = { movieId ->
                startMovieDetailActivity(movieId)
            }

            binding.rvMovie.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = movieAdapter
            }

            homeViewModel.movies.observe(viewLifecycleOwner) { movies ->
                when (movies) {
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        movieAdapter.submitList(movies.data)
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        movies.message?.let {
                            Snackbar.make(view,
                                it, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    private fun startMovieDetailActivity(movieId: String) {
        Timber.d("Movie ID: $movieId")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
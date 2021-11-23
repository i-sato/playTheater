package isato.made.playtheater.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import isato.made.playtheater.core.databinding.ItemListMovieBinding
import isato.made.playtheater.core.util.ext.loadImage
import isato.made.playtheater.core.presentation.model.Movie
import isato.made.playtheater.core.ui.adapter.diffutil.MovieDiffCallback

class MovieAdapter: ListAdapter<Movie, MovieAdapter.ListMovieViewHolder>(MovieDiffCallback()) {

    var onItemClick: ((String, String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMovieViewHolder {
        val itemListMovieBinding =
            ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListMovieViewHolder((itemListMovieBinding))
    }

    override fun onBindViewHolder(holder: ListMovieViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class ListMovieViewHolder(
        private val binding: ItemListMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            with(binding) {
                movie.posterPath?.let {
                    ivItemImage.loadImage(it)
                }
                tvItemTitle.text = movie.title
                tvItemOverview.text = movie.overview
            }
            itemView.setOnClickListener {
                onItemClick?.invoke(movie.movieId, movie.title)
            }
        }
    }

}
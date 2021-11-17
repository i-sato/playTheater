package isato.made.playtheater.core.ui.diffutil

import androidx.recyclerview.widget.DiffUtil
import isato.made.playtheater.core.domain.model.Movie

class MovieDiffCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.movieId == newItem.movieId

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.title == newItem.title && oldItem.overview == newItem.overview
}
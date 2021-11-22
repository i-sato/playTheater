package isato.made.playtheater.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import isato.made.playtheater.model.Genre

class GenreDiffCallback: DiffUtil.ItemCallback<Genre>() {
    override fun areItemsTheSame(oldItem: Genre, newItem: Genre): Boolean =
        oldItem.genreId == newItem.genreId

    override fun areContentsTheSame(oldItem: Genre, newItem: Genre): Boolean =
        oldItem == newItem
}
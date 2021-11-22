package isato.made.playtheater.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import isato.made.playtheater.databinding.ItemGenreBinding
import isato.made.playtheater.model.Genre
import isato.made.playtheater.ui.adapter.diffutil.GenreDiffCallback

class GenreAdapter: ListAdapter<Genre, GenreAdapter.GenreViewHolder>(GenreDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        val itemGenreBinding = ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GenreViewHolder(itemGenreBinding)
    }

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class GenreViewHolder(
        private val binding: ItemGenreBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(genre: Genre) {
            binding.tvGenre.text = genre.name
        }

    }

}
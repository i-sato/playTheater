/*
 * PlayTheater.core
 * GenreAdapter.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import isato.made.playtheater.core.databinding.ItemGenreBinding
import isato.made.playtheater.core.presentation.model.Genre
import isato.made.playtheater.core.ui.adapter.diffutil.GenreDiffCallback

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
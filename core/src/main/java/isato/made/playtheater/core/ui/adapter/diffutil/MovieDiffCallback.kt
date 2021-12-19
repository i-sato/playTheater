/*
 * PlayTheater.core
 * MovieDiffCallback.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.ui.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import isato.made.playtheater.core.presentation.model.Movie

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.movieId == newItem.movieId

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
        oldItem.title == newItem.title && oldItem.overview == newItem.overview
}
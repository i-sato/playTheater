/*
 * PlayTheater.core
 * MovieDomain.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.domain.model

data class MovieDomain(
    val movieId: String,
    val title: String,
    val overview: String? = null,
    val posterPath: String? = null
)

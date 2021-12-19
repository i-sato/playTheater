/*
 * PlayTheater.core
 * MovieDetailDomain.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.domain.model

data class MovieDetailDomain(
    val movieId: String,
    val title: String,
    val overview: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val genres: List<MovieGenreDomain>,
    val releaseDate: String? = null,
    val tagline: String? = null,
    val status: String? = null,
    var isFavorite: Boolean
)

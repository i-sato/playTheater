/*
 * PlayTheater.core
 * PresentationDataMapper.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.util

import isato.made.playtheater.core.domain.model.MovieDetailDomain
import isato.made.playtheater.core.domain.model.MovieDomain
import isato.made.playtheater.core.domain.model.MovieGenreDomain
import isato.made.playtheater.core.presentation.model.Genre
import isato.made.playtheater.core.presentation.model.Movie
import isato.made.playtheater.core.presentation.model.MovieDetail

object PresentationDataMapper {

    fun mapDomainsToPresentations(input: List<MovieDomain>): List<Movie> {
        val movies = mutableListOf<Movie>()
        input.map {
            val movie = Movie(
                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath
            )
            movies.add(movie)
        }
        return movies
    }

    fun mapDetailDomainToPresentation(input: MovieDetailDomain): MovieDetail {
        val genres = mutableListOf<Genre>()
        input.genres.map {
            val genre = Genre(
                genreId = it.genreId,
                name = it.name
            )
            genres.add(genre)
        }
        return with(input) {
            MovieDetail(
                movieId = movieId,
                title = title,
                overview = overview,
                posterPath = posterPath,
                backdropPath = backdropPath,
                genres = genres,
                releaseDate = releaseDate,
                tagline = tagline,
                status = status,
                isFavorite = isFavorite
            )
        }
    }

    fun mapDetailPresentationToDomain(input: MovieDetail): MovieDetailDomain {
        val genres = mutableListOf<MovieGenreDomain>()
        input.genres?.map {
            val genreDomain = MovieGenreDomain(
                genreId = it.genreId,
                name = it.name
            )
            genres.add(genreDomain)
        }
        return with(input) {
            MovieDetailDomain(
                movieId = movieId,
                title = title,
                overview = overview,
                posterPath = posterPath,
                backdropPath = backdropPath,
                genres = genres,
                releaseDate = releaseDate,
                tagline = tagline,
                status = status,
                isFavorite = isFavorite
            )
        }
    }

}
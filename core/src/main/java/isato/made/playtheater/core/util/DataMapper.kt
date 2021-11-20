package isato.made.playtheater.core.util

import isato.made.playtheater.core.data.source.local.entity.MovieEntity
import isato.made.playtheater.core.data.source.remote.response.MovieResponse
import isato.made.playtheater.core.domain.model.MovieDomain

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movies = mutableListOf<MovieEntity>()
        input.map {
            val movieDomain = MovieEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = DateConverter.stringToTimestamp(it.releaseDate)
            )
            movies.add(movieDomain)
        }
        return movies
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<MovieDomain> =
        input.map {
            MovieDomain(
                movieId = it.movieId,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath
            )
        }

}
package isato.made.playtheater.core.util

import isato.made.playtheater.core.data.source.local.entity.GenreEntity
import isato.made.playtheater.core.data.source.local.entity.MovieEntity
import isato.made.playtheater.core.data.source.local.entity.MovieGenreCrossRef
import isato.made.playtheater.core.data.source.local.entity.MovieWithGenre
import isato.made.playtheater.core.data.source.remote.response.MovieDetailResponse
import isato.made.playtheater.core.data.source.remote.response.MovieGenreResponse
import isato.made.playtheater.core.data.source.remote.response.MovieResponse
import isato.made.playtheater.core.domain.model.MovieDetailDomain
import isato.made.playtheater.core.domain.model.MovieDomain
import isato.made.playtheater.core.domain.model.MovieGenreDomain

object DataMapper {

    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity> {
        val movies = mutableListOf<MovieEntity>()
        input.map {
            val movieEntity = MovieEntity(
                movieId = it.id,
                title = it.title,
                overview = it.overview,
                posterPath = it.posterPath,
                backdropPath = it.backdropPath,
                releaseDate = DateConverter.stringToTimestamp(it.releaseDate)
            )
            movies.add(movieEntity)
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

    fun mapDetailResponseToEntity(input: MovieDetailResponse): MovieEntity {
        return with(input) {
            MovieEntity(
                movieId = id,
                title = title,
                overview = overview,
                posterPath = posterPath,
                backdropPath = backdropPath,
                releaseDate = DateConverter.stringToTimestamp(releaseDate),
                tagline = tagline,
                status = status
            )
        }
    }

    fun mapGenreDetailResponsesToEntities(
        input: List<MovieGenreResponse>
    ): List<GenreEntity> {
        val genres = mutableListOf<GenreEntity>()
        input.map {
            val genreEntity = GenreEntity(
                genreId = it.id,
                name = it.name
            )
            genres.add(genreEntity)
        }
        return genres
    }

    fun mapGenreDetailResponsesToGenreRefEntities(
        movieId: String,
        input: List<MovieGenreResponse>
    ): List<MovieGenreCrossRef> {
        val genreCrossRefs = mutableListOf<MovieGenreCrossRef>()
        input.map {
            val movieGenreRefEntity = MovieGenreCrossRef(
                movieId = movieId,
                genreId = it.id
            )
            genreCrossRefs.add(movieGenreRefEntity)
        }
        return genreCrossRefs
    }

    fun mapDetailEntityToDomain(input: MovieWithGenre): MovieDetailDomain {
        val genresDomain = mutableListOf<MovieGenreDomain>()
        input.genres?.map {
            val genre = MovieGenreDomain(
                genreId = it.genreId,
                name = it.name
            )
            genresDomain.add(genre)
        }

        return with(input.movieEntity) {
            MovieDetailDomain(
                movieId = movieId,
                title = title,
                overview = overview,
                posterPath = posterPath,
                backdropPath = backdropPath,
                genres = genresDomain,
                releaseDate = DateConverter.fromTimestamp(releaseDate),
                tagline = tagline,
                status = status,
                isFavorite = isFavorite
            )
        }
    }

}
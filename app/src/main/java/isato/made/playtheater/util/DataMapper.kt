package isato.made.playtheater.util

import isato.made.playtheater.core.domain.model.MovieDetailDomain
import isato.made.playtheater.core.domain.model.MovieDomain
import isato.made.playtheater.core.domain.model.MovieGenreDomain
import isato.made.playtheater.model.Genre
import isato.made.playtheater.model.Movie
import isato.made.playtheater.model.MovieDetail

object DataMapper {

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
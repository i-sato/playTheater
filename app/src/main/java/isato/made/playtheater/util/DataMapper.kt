package isato.made.playtheater.util

import isato.made.playtheater.core.domain.model.MovieDomain
import isato.made.playtheater.model.Movie

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

}
package isato.made.playtheater.core.domain.usecase

import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.domain.model.MovieDetailDomain
import isato.made.playtheater.core.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface MainUseCase {

    fun getAllMovies(): Flow<Resource<List<MovieDomain>>>

    fun getFavoriteMovies(): Flow<List<MovieDomain>>

    fun getMovieById(movieId: String): Flow<Resource<MovieDetailDomain>>

    suspend fun setFavoriteMovie(movieDetailDomain: MovieDetailDomain, newState: Boolean)
}
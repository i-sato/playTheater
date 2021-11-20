package isato.made.playtheater.core.domain.repository

import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    fun getAllMovies(): Flow<Resource<List<MovieDomain>>>

}
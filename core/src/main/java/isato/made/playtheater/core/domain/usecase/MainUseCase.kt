package isato.made.playtheater.core.domain.usecase

import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MainUseCase {
    fun getAllMovies(): Flow<Resource<List<Movie>>>
}
package isato.made.playtheater.core.domain.usecase

import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.domain.model.MovieDomain
import isato.made.playtheater.core.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val mainRepository: MainRepository
) : MainUseCase {

    override fun getAllMovies(): Flow<Resource<List<MovieDomain>>> =
        mainRepository.getAllMovies()
}
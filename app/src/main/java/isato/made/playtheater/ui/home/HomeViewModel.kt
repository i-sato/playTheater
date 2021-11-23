package isato.made.playtheater.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.domain.usecase.MainUseCase
import isato.made.playtheater.core.presentation.model.Movie
import isato.made.playtheater.core.util.PresentationDataMapper
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    mainUseCase: MainUseCase
): ViewModel() {
    val movies = liveData<Resource<List<Movie>>> {
        mainUseCase.getAllMovies().collect {  resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {
                        val data = PresentationDataMapper.mapDomainsToPresentations(it)
                        emit(Resource.Success(data))
                    }
                }
                is Resource.Loading -> {
                    emit (Resource.Loading())
                }
                is Resource.Error -> {
                    resource.message?.let {
                        emit(Resource.Error(it))
                    }
                }
            }
        }
    }
}
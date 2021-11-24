package isato.made.playtheater.detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.domain.usecase.MainUseCase
import isato.made.playtheater.core.presentation.model.MovieDetail
import isato.made.playtheater.core.util.PresentationDataMapper
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
): ViewModel() {

    private val _movieDetail = MutableLiveData<Resource<MovieDetail>>()
    val movieDetail: LiveData<Resource<MovieDetail>> = _movieDetail

    fun getMovieById(movieId: String) {
        viewModelScope.launch {
            mainUseCase.getMovieById(movieId).collect { resource ->
                when(resource) {
                    is Resource.Success -> {
                        val data = PresentationDataMapper.mapDetailDomainToPresentation(resource.data!!)
                        _movieDetail.postValue(Resource.Success(data))
                    }
                    is Resource.Loading -> {
                        _movieDetail.postValue(Resource.Loading())
                    }
                    is Resource.Error -> {
                        _movieDetail.postValue(Resource.Error(resource.message!!))
                    }
                }
            }
        }
    }

    fun setFavoriteMovie() {
        val movieDetail = _movieDetail.value?.data
        if (movieDetail != null) {
            val movieDetailDomain = PresentationDataMapper.mapDetailPresentationToDomain(movieDetail)
            val newState = !movieDetail.isFavorite
            viewModelScope.launch {
                mainUseCase.setFavoriteMovie(movieDetailDomain, newState)
            }
        }
    }

}
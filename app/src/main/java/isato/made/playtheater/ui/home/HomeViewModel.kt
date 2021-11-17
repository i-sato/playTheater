package isato.made.playtheater.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import isato.made.playtheater.core.domain.usecase.MainUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    mainUseCase: MainUseCase
): ViewModel() {
    val movies = mainUseCase.getAllMovies().asLiveData()
}
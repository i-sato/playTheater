package isato.made.playtheater.favorite.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import isato.made.playtheater.core.domain.usecase.MainUseCase
import isato.made.playtheater.core.util.PresentationDataMapper
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ListFavoriteViewModel @Inject constructor(
    mainUseCase: MainUseCase
): ViewModel() {

    val favoriteMovies = liveData {
        mainUseCase.getFavoriteMovies().collect { moviesDomain ->
            val data = PresentationDataMapper.mapDomainsToPresentations(moviesDomain)
            emit(data)
        }
    }

}
package isato.made.playtheater.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import isato.made.playtheater.core.domain.usecase.MainUseCase
import isato.made.playtheater.favorite.ui.list.ListFavoriteViewModel
import javax.inject.Inject

class FavoriteViewModelFactory @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(ListFavoriteViewModel::class.java) -> {
                ListFavoriteViewModel(mainUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}
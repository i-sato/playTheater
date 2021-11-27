package isato.made.playtheater.favorite.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import isato.made.playtheater.favorite.ui.list.ListFavoriteViewModel
import isato.made.playtheater.favorite.viewmodel.FavoriteViewModelFactory

@Module
@InstallIn(FragmentComponent::class)
class FavoriteModule {

    @Provides
    fun provideFavoriteViewModel(fragment: Fragment, factory: FavoriteViewModelFactory) =
        ViewModelProvider(fragment, factory).get(ListFavoriteViewModel::class.java)

}
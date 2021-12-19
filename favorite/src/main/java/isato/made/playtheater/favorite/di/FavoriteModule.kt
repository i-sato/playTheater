/*
 * PlayTheater.favorite
 * FavoriteModule.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

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
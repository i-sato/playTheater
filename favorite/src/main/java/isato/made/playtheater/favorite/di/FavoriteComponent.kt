/*
 * PlayTheater.favorite
 * FavoriteComponent.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.favorite.di

import dagger.Component
import isato.made.playtheater.di.FavoriteModuleDependencies
import isato.made.playtheater.favorite.ui.list.ListFavoriteFragment

@Component(dependencies = [FavoriteModuleDependencies::class])
interface FavoriteComponent {

    @Component.Factory
    interface Factory {
        fun create(favoriteModuleDependencies: FavoriteModuleDependencies): FavoriteComponent
    }

    fun inject(fragment: ListFavoriteFragment)

}
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
package isato.made.playtheater.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import isato.made.playtheater.core.domain.usecase.MainUseCase

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavoriteModuleDependencies {

    fun mainUseCase(): MainUseCase

}
package isato.made.playtheater.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import isato.made.playtheater.core.domain.usecase.MainInteractor
import isato.made.playtheater.core.domain.usecase.MainUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindMainUseCase(mainInteractor: MainInteractor): MainUseCase

}
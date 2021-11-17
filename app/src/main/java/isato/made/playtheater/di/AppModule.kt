package isato.made.playtheater.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import isato.made.playtheater.core.domain.usecase.MainInteractor
import isato.made.playtheater.core.domain.usecase.MainUseCase

@Module
@InstallIn(ViewModelComponent::class)
abstract class AppModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMainUseCase(mainInteractor: MainInteractor): MainUseCase

}
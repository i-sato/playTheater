/*
 * PlayTheater.core
 * MainInteractor.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.domain.usecase

import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.domain.model.MovieDetailDomain
import isato.made.playtheater.core.domain.model.MovieDomain
import isato.made.playtheater.core.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainInteractor @Inject constructor(
    private val mainRepository: MainRepository
) : MainUseCase {

    override fun getAllMovies(): Flow<Resource<List<MovieDomain>>> =
        mainRepository.getAllMovies()

    override fun getFavoriteMovies(): Flow<List<MovieDomain>> = mainRepository.getFavoriteMovies()

    override fun getMovieById(movieId: String): Flow<Resource<MovieDetailDomain>> =
        mainRepository.getMovieById(movieId)

    override suspend fun setFavoriteMovie(movieDetailDomain: MovieDetailDomain, newState: Boolean) =
        mainRepository.setFavoriteMovie(movieDetailDomain, newState)
}
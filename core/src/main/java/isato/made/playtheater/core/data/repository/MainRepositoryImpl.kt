/*
 * PlayTheater.core
 * MainRepositoryImpl.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.data.repository

import isato.made.playtheater.core.data.NetworkBoundResource
import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.data.source.local.LocalDataSource
import isato.made.playtheater.core.data.source.remote.RemoteDataSource
import isato.made.playtheater.core.data.source.remote.network.ApiResponse
import isato.made.playtheater.core.data.source.remote.response.MovieDetailResponse
import isato.made.playtheater.core.data.source.remote.response.MovieResponse
import isato.made.playtheater.core.di.IoDispatcher
import isato.made.playtheater.core.domain.model.MovieDetailDomain
import isato.made.playtheater.core.domain.model.MovieDomain
import isato.made.playtheater.core.domain.repository.MainRepository
import isato.made.playtheater.core.util.DataMapper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : MainRepository {

    override fun getAllMovies(): Flow<Resource<List<MovieDomain>>> =
        object : NetworkBoundResource<List<MovieDomain>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<MovieDomain>> =
                localDataSource.getAllMovies().map {
                    DataMapper.mapEntitiesToDomain(it)
                }

            override fun shouldFetch(data: List<MovieDomain>?): Boolean = true

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movies = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertMovies(movies)
            }
        }.asFlow()

    override fun getFavoriteMovies(): Flow<List<MovieDomain>> {
        return localDataSource.getFavoriteMovies().map { DataMapper.mapEntitiesToDomain(it) }
    }

    override fun getMovieById(movieId: String): Flow<Resource<MovieDetailDomain>> =
        object : NetworkBoundResource<MovieDetailDomain, MovieDetailResponse>() {
            override fun loadFromDB(): Flow<MovieDetailDomain> =
                localDataSource.getMovieById(movieId).map {
                    DataMapper.mapDetailEntityToDomain(it)
                }

            override fun shouldFetch(data: MovieDetailDomain?): Boolean =
                data?.genres.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<MovieDetailResponse>> =
                remoteDataSource.getMovieById(movieId)

            override suspend fun saveCallResult(data: MovieDetailResponse) {
                val movie = DataMapper.mapDetailResponseToEntity(data)
                val genres = data.genres?.let { DataMapper.mapGenreDetailResponsesToEntities(it) }
                val movieGenreCrossRef = data.genres?.let { genreResponses ->
                    DataMapper.mapGenreDetailResponsesToGenreRefEntities(
                        data.id,
                        genreResponses
                    )
                }
                localDataSource.insertMovieGenreAndRefTransaction(movie, genres, movieGenreCrossRef)
            }

        }.asFlow()

    override suspend fun setFavoriteMovie(movieDetailDomain: MovieDetailDomain, newState: Boolean) =
        withContext(ioDispatcher) {
            movieDetailDomain.isFavorite = newState
            val movie = DataMapper.mapDetailDomainToEntity(movieDetailDomain)
            localDataSource.updateMovie(movie)
        }
}
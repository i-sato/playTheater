/*
 * PlayTheater.core
 * MainRepositoryImplTest.kt
 * Created by Isato on 19/12/2021 19:20:17
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.data.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import isato.made.playtheater.core.data.DummyData
import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.data.source.local.LocalDataSource
import isato.made.playtheater.core.data.source.local.entity.MovieEntity
import isato.made.playtheater.core.data.source.remote.RemoteDataSource
import isato.made.playtheater.core.data.source.remote.network.ApiResponse
import isato.made.playtheater.core.domain.repository.MainRepository
import isato.made.playtheater.core.util.DataMapper
import isato.made.playtheater.core.utils.MainCoroutineRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private val movieResponses = DummyData.generateMovieResponses()
    private val movieEntities = DummyData.generateMovieEntities()
    private val movieId = "634649"

    @Mock
    private lateinit var localDataSource: LocalDataSource
    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    private lateinit var mainRepository: MainRepository

    @Before
    fun setup() {
        mainRepository = MainRepositoryImpl(remoteDataSource, localDataSource, Dispatchers.Main)
    }

    @Test
    fun `get empty movies from db, show loading and returns success`() =
        mainCoroutineRule.runBlockingTest {
            val entities = flowOf(emptyList<MovieEntity>())
            val responses = flowOf(ApiResponse.Success(movieResponses))

            `when`(localDataSource.getAllMovies()).thenReturn(entities)
            `when`(remoteDataSource.getAllMovies()).thenReturn(responses)

            val movies = mainRepository.getAllMovies()
            movies.test {
                assertThat(awaitItem() is Resource.Loading).isTrue()
                verify(localDataSource, atLeastOnce()).getAllMovies()
                verify(remoteDataSource, atLeastOnce()).getAllMovies()

                assertThat(awaitItem() is Resource.Loading).isTrue()
                verify(localDataSource).insertMovies(movieEntities)
                assertThat(awaitItem() is Resource.Success).isTrue()
                awaitComplete()
            }
        }

    @Test
    fun `get movies, show loading and returns error`() = mainCoroutineRule.runBlockingTest {
        val flowEntities = flowOf(movieEntities)
        val errorResponses = flowOf(ApiResponse.Error("Invalid URL"))
        `when`(localDataSource.getAllMovies()).thenReturn(flowEntities)
        `when`(remoteDataSource.getAllMovies()).thenReturn(errorResponses)
        val movies = mainRepository.getAllMovies()
        movies.test {
            assertThat(awaitItem() is Resource.Loading).isTrue()
            verify(localDataSource, atLeastOnce()).getAllMovies()
            verify(remoteDataSource, atLeastOnce()).getAllMovies()

            assertThat(awaitItem() is Resource.Loading).isTrue()
            assertThat(awaitItem() is Resource.Error).isTrue()
            awaitComplete()
        }
    }

    @Test
    fun `get favorite movies, returns empty`() = mainCoroutineRule.runBlockingTest {
        val emptyEntities = flowOf(emptyList<MovieEntity>())
        `when`(localDataSource.getFavoriteMovies()).thenReturn(emptyEntities)

        val favoriteMovies = mainRepository.getFavoriteMovies()
        favoriteMovies.test {
            verify(localDataSource).getFavoriteMovies()
            assertThat(awaitItem()).isEmpty()
            awaitComplete()
        }
    }

    @Test
    fun `get favorite movies, returns data is not empty`() = mainCoroutineRule.runBlockingTest {
        val entities = flowOf(movieEntities)
        `when`(localDataSource.getFavoriteMovies()).thenReturn(entities)

        val favoriteMovies = mainRepository.getFavoriteMovies()
        favoriteMovies.test {
            verify(localDataSource).getFavoriteMovies()
            assertThat(awaitItem().size).isEqualTo(3)
            awaitComplete()
        }
    }

    @Test
    fun `get movie detail from db, should not fetch from network`() =
        mainCoroutineRule.runBlockingTest {
            val movieWithGenre = DummyData.generateMovieEntityDetail()
            val movieDetail = flowOf(movieWithGenre)
            `when`(localDataSource.getMovieById(movieId)).thenReturn(movieDetail)

            val movie = mainRepository.getMovieById(movieId)
            movie.test {
                assertThat(awaitItem() is Resource.Loading).isTrue()
                verify(localDataSource, atLeastOnce()).getMovieById(movieId)
                verify(remoteDataSource, never()).getMovieById(movieId)
                assertThat(awaitItem().data?.movieId).isEqualTo(movieId)
                awaitComplete()
            }
        }

    @Test
    fun `get movie detail, should fetch from network and data is not empty`() =
        mainCoroutineRule.runBlockingTest {
            val movieWithGenre = DummyData.generateMovieEntityDetail(false)
            val movieDetailResponse = DummyData.generateMovieDetailResponse()
            val movieDetail = flowOf(movieWithGenre)
            val movieResponse = flowOf(ApiResponse.Success(movieDetailResponse))

            `when`(localDataSource.getMovieById(movieId)).thenReturn(movieDetail)
            `when`(remoteDataSource.getMovieById(movieId)).thenReturn(movieResponse)

            val movie = mainRepository.getMovieById(movieId)
            movie.test {
                assertThat(awaitItem() is Resource.Loading).isTrue()
                verify(localDataSource, atLeastOnce()).getMovieById(movieId)
                verify(remoteDataSource).getMovieById(movieId)
                assertThat(awaitItem() is Resource.Loading).isTrue()
                assertThat(awaitItem().data?.movieId).isEqualTo(movieId)
                awaitComplete()
            }
        }

    @Test
    fun `set favorite movie, should update movie entity`() = mainCoroutineRule.runBlockingTest {
        val movieDetailDomain = DummyData.generateMovieDetailDomain()
        val movieEntity = DataMapper.mapDetailDomainToEntity(movieDetailDomain)
        mainRepository.setFavoriteMovie(movieDetailDomain, true)
        verify(localDataSource).updateMovie(movieEntity)
        assertThat(movieDetailDomain).isNotNull()
    }

}
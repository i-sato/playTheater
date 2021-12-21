/*
 * PlayTheater.core
 * MainUseCaseTest.kt
 * Created by Isato on 20/12/2021 23:42:45
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.domain.usecase

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import isato.made.playtheater.core.data.DummyData
import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.domain.model.MovieDetailDomain
import isato.made.playtheater.core.domain.model.MovieDomain
import isato.made.playtheater.core.domain.repository.MainRepository
import isato.made.playtheater.core.util.DataMapper
import isato.made.playtheater.core.utils.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mainRepository: MainRepository

    private lateinit var mainUseCase: MainUseCase

    private val movieId = "634649"

    @Before
    fun setup() {
        mainUseCase = MainInteractor(mainRepository)
    }

    @Test
    fun `get all movies, returns loading`() = mainCoroutineRule.runBlockingTest {
        val loading = flowOf(Resource.Loading<List<MovieDomain>>())
        `when`(mainRepository.getAllMovies()).thenReturn(loading)

        val movies = mainUseCase.getAllMovies()
        movies.test {
            verify(mainRepository).getAllMovies()
            assertThat(awaitItem() is Resource.Loading).isTrue()
            awaitComplete()
        }
    }

    @Test
    fun `get all movies, returns success and data is not empty`() = mainCoroutineRule.runBlockingTest {
        val movieDomain = DataMapper.mapEntitiesToDomain(DummyData.generateMovieEntities())
        val success = flowOf(Resource.Success(movieDomain))
        `when`(mainRepository.getAllMovies()).thenReturn(success)

        val movies = mainUseCase.getAllMovies()
        movies.test {
            verify(mainRepository).getAllMovies()
            assertThat(awaitItem().data).isEqualTo(movieDomain)
            awaitComplete()
        }
    }

    @Test
    fun `get all movies, returns error`() = mainCoroutineRule.runBlockingTest {
        val error = flowOf(Resource.Error<List<MovieDomain>>("Invalid URL"))
        `when`(mainRepository.getAllMovies()).thenReturn(error)

        val movies = mainUseCase.getAllMovies()
        movies.test {
            verify(mainRepository).getAllMovies()
            assertThat(awaitItem() is Resource.Error).isTrue()
            awaitComplete()
        }
    }

    @Test
    fun `get favorite movies, returns empty list`() = mainCoroutineRule.runBlockingTest {
        val emptyList = flowOf(emptyList<MovieDomain>())
        `when`(mainRepository.getFavoriteMovies()).thenReturn(emptyList)

        val movies = mainUseCase.getFavoriteMovies()
        movies.test {
            verify(mainRepository).getFavoriteMovies()
            assertThat(awaitItem()).isEmpty()
            awaitComplete()
        }
    }

    @Test
    fun `get favorite movies, returns data is not empty`() = mainCoroutineRule.runBlockingTest {
        val movieDomain = DataMapper.mapEntitiesToDomain(DummyData.generateMovieEntities())
        val success = flowOf(movieDomain)
        `when`(mainRepository.getFavoriteMovies()).thenReturn(success)

        val movies = mainUseCase.getFavoriteMovies()
        movies.test {
            verify(mainRepository).getFavoriteMovies()
            assertThat(awaitItem()).isEqualTo(movieDomain)
            awaitComplete()
        }
    }

    @Test
    fun `get movie detail, returns loading`() = mainCoroutineRule.runBlockingTest {
        val loading = flowOf(Resource.Loading<MovieDetailDomain>())
        `when`(mainRepository.getMovieById(movieId)).thenReturn(loading)

        val movie = mainUseCase.getMovieById(movieId)
        movie.test {
            verify(mainRepository).getMovieById(movieId)
            assertThat(awaitItem() is Resource.Loading).isTrue()
            awaitComplete()
        }
    }

    @Test
    fun `get movie detail, returns success and data is not empty`() = mainCoroutineRule.runBlockingTest {
        val movieWithGenre = DummyData.generateMovieEntityDetail()
        val movieDomain = DataMapper.mapDetailEntityToDomain(movieWithGenre)
        val success = flowOf(Resource.Success(movieDomain))
        `when`(mainRepository.getMovieById(movieId)).thenReturn(success)

        val movie = mainUseCase.getMovieById(movieId)
        movie.test {
            verify(mainRepository).getMovieById(movieId)
            assertThat(awaitItem().data).isEqualTo(movieDomain)
            awaitComplete()
        }
    }

    @Test
    fun `get movie detail, returns error`() = mainCoroutineRule.runBlockingTest {
        val error = flowOf(Resource.Error<MovieDetailDomain>("Invalid URL"))
        `when`(mainRepository.getMovieById(movieId)).thenReturn(error)

        val movie = mainUseCase.getMovieById(movieId)
        movie.test {
            verify(mainRepository).getMovieById(movieId)
            assertThat(awaitItem() is Resource.Error).isTrue()
            awaitComplete()
        }
    }

    @Test
    fun `set favorite movie, should update movie`() = mainCoroutineRule.runBlockingTest {
        val movieDetailDomain = DummyData.generateMovieDetailDomain()
        mainUseCase.setFavoriteMovie(movieDetailDomain, true)
        verify(mainRepository).setFavoriteMovie(movieDetailDomain, true)
        assertThat(movieDetailDomain).isNotNull()
    }

}
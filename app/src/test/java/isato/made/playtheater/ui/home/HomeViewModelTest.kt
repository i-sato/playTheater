/*
 * PlayTheater.app
 * HomeViewModelTest.kt
 * Created by Isato on 20/12/2021 23:32:31
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.domain.model.MovieDomain
import isato.made.playtheater.core.domain.usecase.MainUseCase
import isato.made.playtheater.core.util.DataMapper
import isato.made.playtheater.testlib.data.DummyData
import isato.made.playtheater.testlib.utils.MainCoroutineRule
import isato.made.playtheater.testlib.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mainUseCase: MainUseCase

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setup() {
        viewModel = HomeViewModel(mainUseCase)
    }

    @Test
    fun `get movies, returns loading and data is empty`() {
        val loading = flowOf(Resource.Loading<List<MovieDomain>>())
        `when`(mainUseCase.getAllMovies()).thenReturn(loading)

        val movies = viewModel.movies.getOrAwaitValue()
        verify(mainUseCase).getAllMovies()
        assertThat(movies is Resource.Loading).isTrue()
        assertThat(movies.data).isNull()
    }

    @Test
    fun `get movies, returns success and data is not empty`() {
        val movieDomain = DataMapper.mapEntitiesToDomain(DummyData.generateMovieEntities())
        val success = flowOf(Resource.Success(movieDomain))
        `when`(mainUseCase.getAllMovies()).thenReturn(success)

        val movies = viewModel.movies.getOrAwaitValue()
        verify(mainUseCase).getAllMovies()
        assertThat(movies is Resource.Success).isTrue()
        assertThat(movies.data).isNotEmpty()
    }

    @Test
    fun `get movies, returns error and data is empty`() {
        val error = flowOf(Resource.Error<List<MovieDomain>>("Invalid URL"))
        `when`(mainUseCase.getAllMovies()).thenReturn(error)

        val movies = viewModel.movies.getOrAwaitValue()
        verify(mainUseCase).getAllMovies()
        assertThat(movies is Resource.Error).isTrue()
        assertThat(movies.message).isEqualTo("Invalid URL")
        assertThat(movies.data).isNull()
    }
}
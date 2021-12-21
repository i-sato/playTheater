/*
 * PlayTheater.detail
 * DetailViewModelTest.kt
 * Created by Isato on 21/12/2021 10:17:9
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.detail.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import isato.made.playtheater.core.data.Resource
import isato.made.playtheater.core.domain.model.MovieDetailDomain
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
class DetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mainUseCase: MainUseCase

    private lateinit var viewModel: DetailViewModel

    private val movieId = "634649"

    @Before
    fun setup() {
        viewModel = DetailViewModel(mainUseCase)
    }

    @Test
    fun `get movie detail, returns loading`() {
        val loading = flowOf(Resource.Loading<MovieDetailDomain>())
        `when`(mainUseCase.getMovieById(movieId)).thenReturn(loading)

        viewModel.getMovieById(movieId)
        val movie = viewModel.movieDetail.getOrAwaitValue()
        verify(mainUseCase).getMovieById(movieId)
        assertThat(movie is Resource.Loading).isTrue()
    }

    @Test
    fun `get movie detail, returns success and data is not empty`() {
        val movieWithGenre = DummyData.generateMovieEntityDetail()
        val movieDomain = DataMapper.mapDetailEntityToDomain(movieWithGenre)
        val success = flowOf(Resource.Success(movieDomain))
        `when`(mainUseCase.getMovieById(movieId)).thenReturn(success)

        viewModel.getMovieById(movieId)
        val movie = viewModel.movieDetail.getOrAwaitValue()
        verify(mainUseCase).getMovieById(movieId)
        assertThat(movie is Resource.Success).isTrue()
        assertThat(movie.data).isNotNull()
    }

    @Test
    fun `get movie detail, returns error and data is empty`() {
        val error = flowOf(Resource.Error<MovieDetailDomain>("Invalid URL"))
        `when`(mainUseCase.getMovieById(movieId)).thenReturn(error)

        viewModel.getMovieById(movieId)
        val movie = viewModel.movieDetail.getOrAwaitValue()
        verify(mainUseCase).getMovieById(movieId)
        assertThat(movie is Resource.Error).isTrue()
        assertThat(movie.message).isEqualTo("Invalid URL")
        assertThat(movie.data).isNull()
    }

}
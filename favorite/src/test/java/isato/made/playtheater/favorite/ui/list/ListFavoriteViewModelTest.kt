/*
 * PlayTheater.favorite
 * ListFavoriteViewModelTest.kt
 * Created by Isato on 21/12/2021 11:20:16
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.favorite.ui.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import isato.made.playtheater.core.domain.model.MovieDomain
import isato.made.playtheater.core.domain.usecase.MainUseCase
import isato.made.playtheater.core.util.DataMapper
import isato.made.playtheater.core.util.PresentationDataMapper
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
class ListFavoriteViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var mainUseCase: MainUseCase

    private lateinit var viewModel: ListFavoriteViewModel

    @Before
    fun setup() {
        viewModel = ListFavoriteViewModel(mainUseCase)
    }

    @Test
    fun `get favorite movies, returns empty list`() {
        val emptyList = flowOf(emptyList<MovieDomain>())
        `when`(mainUseCase.getFavoriteMovies()).thenReturn(emptyList)

        val movies = viewModel.favoriteMovies.getOrAwaitValue()
        verify(mainUseCase).getFavoriteMovies()
        assertThat(movies).isEmpty()
    }

    @Test
    fun `get favorite movies, returns data is not empty`() {
        val movieDomain = DataMapper.mapEntitiesToDomain(DummyData.generateMovieEntities())
        val success = flowOf(movieDomain)
        `when`(mainUseCase.getFavoriteMovies()).thenReturn(success)

        val movies = viewModel.favoriteMovies.getOrAwaitValue()
        verify(mainUseCase).getFavoriteMovies()
        assertThat(movies).isEqualTo(PresentationDataMapper.mapDomainsToPresentations(movieDomain))
    }

}
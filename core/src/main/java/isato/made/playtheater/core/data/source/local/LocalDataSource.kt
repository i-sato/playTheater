/*
 * PlayTheater.core
 * LocalDataSource.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.data.source.local

import isato.made.playtheater.core.data.source.local.entity.GenreEntity
import isato.made.playtheater.core.data.source.local.entity.MovieEntity
import isato.made.playtheater.core.data.source.local.entity.MovieGenreCrossRef
import isato.made.playtheater.core.data.source.local.entity.MovieWithGenre
import isato.made.playtheater.core.data.source.local.room.MovieDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(
    private val movieDao: MovieDao
) {

    fun getAllMovies(): Flow<List<MovieEntity>> = movieDao.getAllMovies()

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = movieDao.getFavoriteMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun getMovieById(movieId: String): Flow<MovieWithGenre> = movieDao.getMovieById(movieId)

    suspend fun insertMovieGenreAndRefTransaction(
        movie: MovieEntity,
        genres: List<GenreEntity>?,
        movieGenreCrossRef: List<MovieGenreCrossRef>?
    ) = movieDao.insertMovieGenreAndRefTransaction(movie, genres, movieGenreCrossRef)

    suspend fun updateMovie(movie: MovieEntity) = movieDao.updateMovie(movie)

}
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

    suspend fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun getMovieById(movieId: String): Flow<MovieWithGenre> = movieDao.getMovieById(movieId)

    suspend fun insertMovieGenreAndRefTransaction(
        movie: MovieEntity,
        genres: List<GenreEntity>?,
        movieGenreCrossRef: List<MovieGenreCrossRef>?
    ) = movieDao.insertMovieGenreAndRefTransaction(movie, genres, movieGenreCrossRef)

}
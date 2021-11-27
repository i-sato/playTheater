package isato.made.playtheater.core.data.source.local.room

import androidx.room.*
import isato.made.playtheater.core.data.source.local.entity.GenreEntity
import isato.made.playtheater.core.data.source.local.entity.MovieEntity
import isato.made.playtheater.core.data.source.local.entity.MovieGenreCrossRef
import isato.made.playtheater.core.data.source.local.entity.MovieWithGenre
import kotlinx.coroutines.flow.Flow

@Dao
abstract class MovieDao {

    @Query("SELECT * FROM movie")
    abstract fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    abstract fun getFavoriteMovies(): Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertMovies(movies: List<MovieEntity>)

    @Transaction
    @Query("SELECT * FROM movie WHERE movieId = :movieId")
    abstract fun getMovieById(movieId: String): Flow<MovieWithGenre>

    @Transaction
    open suspend fun insertMovieGenreAndRefTransaction(
        movie: MovieEntity,
        genres: List<GenreEntity>?,
        movieGenreCrossRef: List<MovieGenreCrossRef>?
    ) {
        updateMovie(movie)
        insertGenres(genres)
        insertMovieGenreCrossRef(movieGenreCrossRef)
    }

    @Update
    abstract suspend fun updateMovie(movie: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertGenres(genres: List<GenreEntity>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertMovieGenreCrossRef(movieGenreCrossRef: List<MovieGenreCrossRef>?)

}
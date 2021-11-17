package isato.made.playtheater.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import isato.made.playtheater.core.data.source.local.room.MovieDao
import isato.made.playtheater.core.data.source.local.room.MovieDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase = Room.databaseBuilder(
        context,
        MovieDatabase::class.java, "playTheater.db"
    ).build()

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao =
        database.movieDao()

}
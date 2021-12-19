/*
 * PlayTheater.core
 * DatabaseModule.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import isato.made.playtheater.core.BuildConfig
import isato.made.playtheater.core.BuildConfig.DB_KEY
import isato.made.playtheater.core.data.source.local.room.MovieDao
import isato.made.playtheater.core.data.source.local.room.MovieDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): MovieDatabase {
        val dbBuilder = Room.databaseBuilder(
            context,
            MovieDatabase::class.java, "playTheater.db"
        ).also { builder ->
            when {
                BuildConfig.DEBUG -> builder.fallbackToDestructiveMigration()
                else -> {
                    val factory = SupportFactory(SQLiteDatabase.getBytes(DB_KEY.toCharArray()))
                    builder.openHelperFactory(factory)
                }
            }
        }
        return dbBuilder.build()
    }

    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao =
        database.movieDao()

}
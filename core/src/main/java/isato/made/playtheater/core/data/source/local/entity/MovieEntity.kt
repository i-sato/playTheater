/*
 * PlayTheater.core
 * MovieEntity.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import isato.made.playtheater.core.util.DateConverter

@Entity(tableName = "movie")
data class MovieEntity (

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    val movieId: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "overview")
    val overview: String? = null,

    @ColumnInfo(name = "poster_path")
    val posterPath: String? = null,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String? = null,

    @TypeConverters(DateConverter::class)
    @ColumnInfo(name = "release_date")
    val releaseDate: Long?,

    @ColumnInfo(name = "status")
    val status: String? = null,

    @ColumnInfo(name = "tagline")
    val tagline: String? = null,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false

)
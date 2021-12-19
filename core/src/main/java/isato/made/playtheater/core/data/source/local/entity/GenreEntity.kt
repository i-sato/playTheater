/*
 * PlayTheater.core
 * GenreEntity.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "genreId")
    val genreId: String,

    @ColumnInfo(name = "name")
    val name: String

)

/*
 * PlayTheater.core
 * MovieGenreResponse.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieGenreResponse(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: String
)

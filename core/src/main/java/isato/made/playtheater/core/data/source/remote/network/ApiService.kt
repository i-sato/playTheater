/*
 * PlayTheater.core
 * ApiService.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.data.source.remote.network

import isato.made.playtheater.core.BuildConfig.API_KEY_QUERY
import isato.made.playtheater.core.data.source.remote.response.ListMovieResponse
import isato.made.playtheater.core.data.source.remote.response.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/now_playing$API_KEY_QUERY")
    suspend fun getMovies(): ListMovieResponse

    @GET("movie/{movie_id}$API_KEY_QUERY")
    suspend fun getMovieById(
        @Path("movie_id") movieId: String
    ): MovieDetailResponse

}
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
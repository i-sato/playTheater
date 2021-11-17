package isato.made.playtheater.core.data.source.remote.network

import isato.made.playtheater.core.BuildConfig.API_KEY_QUERY
import isato.made.playtheater.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET

interface ApiService {

    @GET("movie/now_playing$API_KEY_QUERY")
    suspend fun getMovies(): ListMovieResponse

}
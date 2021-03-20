package me.farhan.moviecataloqlite.network

import me.farhan.moviecataloqlite.BuildConfig.API_KEY
import me.farhan.moviecataloqlite.model.Movie
import me.farhan.moviecataloqlite.network.response.TmdbResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author farhan
 * created at at 8:28 on 03/03/21.
 */
interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String? = API_KEY,
        @Query("page") page: Int = 1
    ): Call<TmdbResponse<Movie>>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String? = API_KEY,
    ): Call<Movie>
}
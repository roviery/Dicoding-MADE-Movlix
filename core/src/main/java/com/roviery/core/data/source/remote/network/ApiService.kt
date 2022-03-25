package com.roviery.core.data.source.remote.network

import com.roviery.core.data.source.remote.response.ListMovieResponse
import com.roviery.core.data.source.remote.response.ListTvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // Movie
    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") api_key: String,
        @Query("page") page: String
    ): ListMovieResponse

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: String
    ): ListMovieResponse

    // TvShow
    @GET("tv/popular")
    suspend fun getPopularTvShow(
        @Query("api_key") api_key: String,
        @Query("page") page: String
    ): ListTvShowResponse

    @GET("search/tv")
    suspend fun searchTvShow(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
        @Query("page") page: String
    ): ListTvShowResponse

}
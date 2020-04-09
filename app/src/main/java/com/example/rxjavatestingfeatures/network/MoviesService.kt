package com.example.rxjavatestingfeatures.network

import com.example.rxjavatestingfeatures.data.models.toprated.Movies
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET("/3/movie/top_rated")
    fun getTopRatedMovieFromApi(@Query("api_key") app_id: String, @Query("page") page: String): Single<Movies>

    @GET("/3/movie/popular")
    fun getPopularMovieFromApi(@Query("api_key") app_id: String, @Query("page") page: String): Single<Movies>
}
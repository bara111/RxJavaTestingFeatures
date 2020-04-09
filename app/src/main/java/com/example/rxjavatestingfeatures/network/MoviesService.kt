package com.example.rxjavatestingfeatures.network

import com.example.rxjavatestingfeatures.data.models.toprated.Movies
import com.example.rxjavatestingfeatures.data.models.trailers.Trailers
import io.reactivex.MaybeSource
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesService {
    @GET("/3/movie/top_rated")
    fun getMovieFromApi(@Query("api_key") app_id: String, @Query("page") page: String): Single<Response<Movies>>

    @GET("/3/movie/{id}/videos")
    fun getMovieTrailersFromApi(
        @Path("id") id: Int,@Query("api_key") token: String?
    ): Single<Response<Trailers>>
}
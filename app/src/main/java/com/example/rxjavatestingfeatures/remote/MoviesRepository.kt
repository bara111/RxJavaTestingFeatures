package com.example.rxjavatestingfeatures.remote

import android.content.Context
import com.example.rxjavatestingfeatures.R
import com.example.rxjavatestingfeatures.data.models.DataAll
import com.example.rxjavatestingfeatures.data.models.toprated.Movies
import com.example.rxjavatestingfeatures.network.MoviesService
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MoviesRepository @Inject constructor(
    private val moviesService: MoviesService,
    private val context: Context
) {

    fun requestTopRatedMoviesAndPopular(): Single<DataAll> {
        return Single.zip(
            moviesService.getPopularMovieFromApi(
                context.getString(R.string.api_key),
                context.getString(R.string.page_one)
            ),
            moviesService.getTopRatedMovieFromApi(
                context.getString(R.string.api_key),
                context.getString(R.string.page_one)
            ),
            BiFunction<Movies?, Movies?, DataAll> { topRated: Movies, popular: Movies ->
                DataAll(
                    topRated,
                    popular
                )
            }
        ).subscribeOn(Schedulers.io())
    }
}

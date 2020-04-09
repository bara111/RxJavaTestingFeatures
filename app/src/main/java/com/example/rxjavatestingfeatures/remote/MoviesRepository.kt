package com.example.rxjavatestingfeatures.remote

import android.content.Context
import com.example.rxjavatestingfeatures.R
import com.example.rxjavatestingfeatures.data.models.trailers.Trailers
import com.example.rxjavatestingfeatures.network.MoviesService
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import retrofit2.http.Path
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesService: MoviesService,
    private val context: Context
) {
    fun requestTopRatedMoviesFromApi() =
        moviesService.getMovieFromApi(
            context.getString(R.string.api_key), context.getString(R.string.page_one)
        ).subscribeOn(Schedulers.io())


    fun requestTrailersOfMovieId(id: Int) =
        moviesService.getMovieTrailersFromApi( id,context.getString(R.string.api_key))

    }

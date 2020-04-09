package com.example.rxjavatestingfeatures.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavatestingfeatures.data.models.toprated.Movies
import com.example.rxjavatestingfeatures.data.models.trailers.Trailers
import com.example.rxjavatestingfeatures.remote.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Response
import javax.inject.Inject

class MainViewModel @Inject constructor(
    moviesRepository: MoviesRepository
) : ViewModel() {
    private val disposable = CompositeDisposable()
    private var _moviesData: MutableLiveData<Movies> = MutableLiveData()
    val moviesData: LiveData<Movies> get() = _moviesData

    init {
        disposable.add(
            moviesRepository.requestTopRatedMoviesFromApi()
                .flatMap { return@flatMap moviesRepository.requestTrailersOfMovieId(it.body()?.results!![0].id) }
                .subscribeOn(Schedulers.io()).filter { it.body()?.results?.isNotEmpty()!! }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    ::onTrailerDataSuccess,
                    ::onErrorTrailer
                )
        )

    }

    private fun onTrailerDataSuccess(trailersResponse: Response<Trailers>) {
        Log.d(MAIN_ACTIVITY, trailersResponse.body().toString())
    }

    private fun onErrorTrailer(throwable: Throwable) {
        Log.d(MAIN_ACTIVITY, throwable.toString())

    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    companion object {
        private val MAIN_ACTIVITY: String = MainActivity::class.java.name

    }
}




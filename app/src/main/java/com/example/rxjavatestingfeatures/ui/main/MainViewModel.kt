package com.example.rxjavatestingfeatures.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rxjavatestingfeatures.data.models.DataAll
import com.example.rxjavatestingfeatures.data.models.toprated.Movie
import com.example.rxjavatestingfeatures.remote.MoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import javax.inject.Inject

class MainViewModel @Inject constructor(
    moviesRepository: MoviesRepository
) : ViewModel() {
    private val disposable = CompositeDisposable()
    private var _moviesTopRatedData: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesTopRatedData: LiveData<List<Movie>> get() = _moviesTopRatedData
    private var _moviesPopularData: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesPopularData: LiveData<List<Movie>> get() = _moviesPopularData
    private var _isLoadingProgressBar: MutableLiveData<Boolean> = MutableLiveData(true)
    val isLoadingProgressBar: LiveData<Boolean> get() = _isLoadingProgressBar

    init {
        disposable.add(
            moviesRepository.requestTopRatedMoviesAndPopular()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<DataAll>() {
                    override fun onSuccess(movies: DataAll) {
                        _moviesPopularData.value = movies.popular.results
                        _moviesTopRatedData.value = movies.topRated.results
                        _isLoadingProgressBar.value = false

                    }

                    override fun onError(e: Throwable) {
                        Log.d(MAIN_ACTIVITY, e.message.toString())
                        _isLoadingProgressBar.value = true

                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    companion object {
        private val MAIN_ACTIVITY: String = MainActivity::class.java.name

    }
}




package com.example.rxjavatestingfeatures.di.module

import com.example.rxjavatestingfeatures.network.MoviesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object RetrofitModule {
    @Singleton
    @Provides
    fun getRetrofit(): MoviesService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(MoviesService::class.java)
    }

    private const val BASE_URL = "https://api.themoviedb.org/"
}

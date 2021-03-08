package ru.study.photogalleryv3.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.study.photogalleryv3.network.ApiProvider
import ru.study.photogalleryv3.Constants
import ru.study.photogalleryv3.network.FlickrApi
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideFlickrApi(apiProvider: ApiProvider): FlickrApi {
        return apiProvider.provide()
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
package ru.study.photogalleryv3.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.study.photogalleryv3.FlickrApi

@Module
@InstallIn(ViewModelComponent::class)
object RetrofitModule {

    private const val BASE_URL = "https://api.flickr.com/services/rest/"

    @Provides
    fun providesRetrofitService(): FlickrApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FlickrApi::class.java)
    }
}
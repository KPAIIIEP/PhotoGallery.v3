package ru.study.photogalleryv3

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {
    @GET("?method=flickr.photos.getRecent&format=json&nojsoncallback=1&extras=url_s")
    fun getPhotos(@Query("api_key") api_key: String = API_KEY): Observable<FlickrPhotosResponse>

    companion object {
        private const val BASE_URL = "https://api.flickr.com/services/rest/"
        val flickrApi: FlickrApi = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FlickrApi::class.java)
    }
}
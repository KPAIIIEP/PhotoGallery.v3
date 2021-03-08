package ru.study.photogalleryv3.network

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import ru.study.photogalleryv3.API_KEY
import ru.study.photogalleryv3.model.FlickrPhotosResponse

interface FlickrApi {

    @GET("?method=flickr.photos.getRecent&format=json&nojsoncallback=1&extras=url_s")
    fun getPhotos(@Query("api_key") api_key: String = API_KEY): Observable<FlickrPhotosResponse>
}
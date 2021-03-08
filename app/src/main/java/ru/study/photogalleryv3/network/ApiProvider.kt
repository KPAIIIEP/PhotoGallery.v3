package ru.study.photogalleryv3.network

import retrofit2.Retrofit
import javax.inject.Inject

class ApiProvider @Inject constructor(private val retrofit: Retrofit) {

    fun provide(): FlickrApi = retrofit.create(FlickrApi::class.java)
}
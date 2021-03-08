package ru.study.photogalleryv3.model

import com.google.gson.annotations.SerializedName

class FlickrPhotosResponse(
        @SerializedName("photos")
        val response: Photos
)
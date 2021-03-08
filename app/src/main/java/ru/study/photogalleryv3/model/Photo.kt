package ru.study.photogalleryv3.model

import com.google.gson.annotations.SerializedName

class Photo(
        @SerializedName("id")
        val id: String,
        @SerializedName("title")
        val caption: String,
        @SerializedName("url_s")
        val url: String
)
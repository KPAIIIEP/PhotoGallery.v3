package ru.study.photogalleryv3

import com.google.gson.annotations.SerializedName

data class Photo(
        @SerializedName("id")
        val id: String,
        @SerializedName("title")
        val caption: String,
        @SerializedName("url_s")
        val url: String
)
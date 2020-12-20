package ru.study.photogalleryv3

import com.google.gson.annotations.SerializedName

data class Photos(
        @SerializedName("photo")
        val photos: List<Photo>
)
package ru.study.photogalleryv3.model

import com.google.gson.annotations.SerializedName

class Photos(
        @SerializedName("photo")
        val photos: List<Photo>
)
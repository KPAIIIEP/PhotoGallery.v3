package ru.study.photogalleryv3.ui

import ru.study.photogalleryv3.model.Photo

data class PhotosUiState(
    val isLoading: Boolean = false,
    val photos: List<Photo> = listOf(),
    val errorMessage: String? = null
)
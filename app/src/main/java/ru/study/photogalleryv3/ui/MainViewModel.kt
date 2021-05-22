package ru.study.photogalleryv3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.study.photogalleryv3.FlickrApi
import ru.study.photogalleryv3.model.Photo

class MainViewModel : ViewModel() {

    private var posts = MutableStateFlow<List<Photo>>(listOf())

    init {
        updatePosts()
    }

    fun observablePosts(): Flow<List<Photo>> = posts

    fun updatePosts() {
        viewModelScope.launch {
            FlickrApi.flickrApi.getPhotos()
                .map { result -> result.response.photos }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { photos ->
                    posts.value = photos
                }
        }
    }
}
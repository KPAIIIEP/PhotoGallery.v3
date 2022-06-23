package ru.study.photogalleryv3.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.study.photogalleryv3.API_KEY
import ru.study.photogalleryv3.FlickrApi
import ru.study.photogalleryv3.model.FlickrPhotosResponse
import java.io.IOException

class PhotosViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(PhotosUiState())
    val uiState: StateFlow<PhotosUiState> = _uiState.asStateFlow()

    private val flickrApi = FlickrApi.flickrApi

    private var fetchJob: Job? = null

    init {
        fetchPhotos()
    }

    fun fetchPhotos() {
        fetchJob?.cancel()
        fetchJob = viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            delay(2000L) // искусственная задержка
            try {
                flickrApi.getPhotos(API_KEY).enqueue(object : Callback<FlickrPhotosResponse> {
                    override fun onResponse(
                        call: Call<FlickrPhotosResponse>,
                        response: Response<FlickrPhotosResponse>
                    ) {
                        val photos = response.body()?.response?.photos
                        _uiState.update {
                            it.copy(photos = photos ?: listOf(), isLoading = false)
                        }
                    }
                    override fun onFailure(call: Call<FlickrPhotosResponse>, t: Throwable) {
                        _uiState.update {
                            it.copy(errorMessage = t.message)
                        }
                    }
                })
            } catch (ioe: IOException) {
                _uiState.update {
                    it.copy(errorMessage = ioe.message)
                }
            }
        }
    }

    fun userMessageShown() {
        _uiState.update {
            it.copy(errorMessage = null)
        }
    }
}
package com.example.tagyourit.ui.photos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tagyourit.BuildConfig.API_KEY
import com.example.tagyourit.data.model.PhotoSearchResponse
import com.example.tagyourit.data.repo.PhotoRepo
import kotlinx.coroutines.launch
import com.example.tagyourit.utils.Resource
import com.example.tagyourit.utils.extensions.successWithData
import retrofit2.Response


class PhotoViewModel @ViewModelInject constructor(
    private val repository: PhotoRepo
) : ViewModel() {

    private val _photos = MutableLiveData<Resource<PhotoSearchResponse>>()
    val photoObservable: LiveData<Resource<PhotoSearchResponse>>
        get() = _photos

    init {
        loadPhotos()
    }

    private fun fetchPhotos(search: String, per_page: Int? = null, pageNum: Int? = null) {
        toggleLoading(_photos)
        viewModelScope.launch {
            val response = when{
                search.isNotEmpty() -> pageNum?.let { repository.getPhotos(search, 10,1, API_KEY) }
                else -> repository.getPhotos("dogs", 10, 1, API_KEY)
            }
            if (response != null) {
                handleResponse(_photos, response)
            }

        }

    }

    private fun loadPhotos(loadOption: LOAD? = null) {
        _photos.value?.data?.let {
            val link = when (loadOption) {
                LOAD.NEXT -> it.next_page
                else -> null
            }
            if (link != null) {
                fetchPhotos(link)
            }
        } ?: fetchPhotos("Dogs")
    }

    private fun <T> toggleLoading(mutableLiveData: MutableLiveData<Resource<T>>) {
        mutableLiveData.value = Resource.loading()
    }

    private fun <T> handleResponse(
        mutableLiveData: MutableLiveData<Resource<T>>,
        response: Response<PhotoSearchResponse>
    ) {
        val resource = when {
            response.successWithData() -> Resource.success(response.body())
            else -> Resource.error("Something went wrong: ${response.message()}")
        }
        mutableLiveData.postValue(resource as Resource<T>?)
    }


    enum class LOAD {
        NEXT
    }
}
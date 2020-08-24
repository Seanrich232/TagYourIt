package com.example.tagyourit.ui.photos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tagyourit.data.model.PhotoSearchResponse
import com.example.tagyourit.data.repo.PhotoRepo
import kotlinx.coroutines.launch
import com.example.tagyourit.utils.Resource
import com.example.tagyourit.utils.extensions.successWithData
import retrofit2.Response


class PhotoViewModel @ViewModelInject constructor(
    private val repository: PhotoRepo
) : ViewModel() {

    val photos = repository.getPhotos("cats", 10,1)
}
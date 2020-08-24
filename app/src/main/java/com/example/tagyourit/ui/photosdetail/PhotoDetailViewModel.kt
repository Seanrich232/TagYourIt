package com.example.tagyourit.ui.photosdetail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.tagyourit.data.model.Photo
import com.example.tagyourit.data.repo.PhotoRepo
import com.example.tagyourit.utils.Resource

class PhotoDetailViewModel @ViewModelInject constructor(
    private val repository: PhotoRepo
) : ViewModel() {

    private val _id = MutableLiveData<Int>()

    private val _photos = _id.switchMap { id ->
        repository.getPhoto(id)
    }
    val photo: LiveData<Resource<Photo>> = _photos


    fun start(id: Int) {
        _id.value = id
    }

}

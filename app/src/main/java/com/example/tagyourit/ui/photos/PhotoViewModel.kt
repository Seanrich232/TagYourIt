package com.example.tagyourit.ui.photos

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.tagyourit.data.repo.PhotoRepo

class   PhotoViewModel @ViewModelInject constructor(
    private val repository: PhotoRepo
) : ViewModel() {

    val photos = repository.getPhotos(10, 1)

}
package com.example.tagyourit.data.repo

import com.example.tagyourit.data.api.PhotoService
import com.example.tagyourit.data.model.PhotoSearchResponse
import retrofit2.Response
import javax.inject.Inject

class PhotoRepo @Inject constructor(
    private val photoService: PhotoService
) {

    suspend fun getPhotos() : Response<PhotoSearchResponse> =
        photoService.getPhotos(10,1)
}
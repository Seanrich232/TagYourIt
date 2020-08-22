package com.example.tagyourit.data.repo

import com.example.tagyourit.data.api.PhotoDataSource
import com.example.tagyourit.data.api.PhotoService
import com.example.tagyourit.data.model.PhotoSearchResponse
import com.example.tagyourit.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class PhotoRepo @Inject constructor(
    private val photoDataSource: PhotoDataSource
) {

    suspend fun getPhotos(perPage: Int, page: Int) : Resource<PhotoSearchResponse> =
        photoDataSource.getPhotos(10, 1)
}
package com.example.tagyourit.data.repo

import com.example.tagyourit.data.api.PhotoService
import com.example.tagyourit.data.model.PhotoSearchResponse
import com.example.tagyourit.utils.Resource
import retrofit2.Response
import javax.inject.Inject

class PhotoRepo @Inject constructor(
    private val photoDataSource: PhotoService
) {

    suspend fun getPhotos(perPage: Int, page: Int) : Response<PhotoSearchResponse> =
        photoDataSource.getPhotos(10, 1)

    suspend fun getPhotosTest(theme: String) : Response<PhotoSearchResponse> =
        photoDataSource.getPhotosTest(theme)
}
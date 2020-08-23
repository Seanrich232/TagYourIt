package com.example.tagyourit.data.repo

import com.example.tagyourit.data.api.PhotoService
import com.example.tagyourit.data.model.PhotoSearchResponse
import retrofit2.Response
import javax.inject.Inject

class PhotoRepo @Inject constructor(
    private val photoDataSource: PhotoService
) {

    suspend fun getPhotos(
        search: String,
        perPage: Int,
        page: Int,
        api_key: String
    ): Response<PhotoSearchResponse> =
        photoDataSource.getPhotos(search, perPage, page, api_key)
}
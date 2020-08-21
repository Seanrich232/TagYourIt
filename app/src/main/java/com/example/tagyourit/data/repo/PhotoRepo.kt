package com.example.tagyourit.data.repo

import com.example.tagyourit.data.api.PhotoService
import com.example.tagyourit.data.model.SearchResponse
import retrofit2.Response

class PhotoRepo(private val photoService: PhotoService) {

    suspend fun getPhotos() : Response<SearchResponse> =
        photoService.getPhotos(20,1)
}
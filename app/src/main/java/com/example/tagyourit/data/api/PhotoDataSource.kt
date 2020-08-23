package com.example.tagyourit.data.api

import javax.inject.Inject

class PhotoDataSource @Inject constructor(
    private val photoService: PhotoService
) : BaseDataSource() {

    suspend fun getPhotos(perPage: Int, page: Int) = getResult { photoService.getPhotos(perPage, page) }
}
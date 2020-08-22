package com.example.tagyourit.data.repo

import com.example.tagyourit.data.api.PhotoDataSource
import com.example.tagyourit.data.api.PhotoService
import com.example.tagyourit.data.local.PhotoDao
import com.example.tagyourit.data.model.PhotoSearchResponse
import com.example.tagyourit.utils.Resource
import com.example.tagyourit.utils.performGetOperation
import retrofit2.Response
import javax.inject.Inject

class PhotoRepo @Inject constructor(
    private val photoDataSource: PhotoDataSource,
    private val localDataSource: PhotoDao
) {

//    TODO we should be able to delete this after we confirm the api call works
//    suspend fun getPhotos(perPage: Int, page: Int) : Resource<PhotoSearchResponse> =
//        photoDataSource.getPhotos(10, 1)

    fun getPhotos(perPage: Int, page: Int)= performGetOperation(
        databaseQuery = { localDataSource.getAllPhotos() },
        networkCall = { photoDataSource.getPhotos(10,1)}
    )

}
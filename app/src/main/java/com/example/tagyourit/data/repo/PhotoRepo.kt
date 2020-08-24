package com.example.tagyourit.data.repo

import com.example.tagyourit.BuildConfig.API_KEY
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

    fun getPhotos(search: String, perPage: Int, page: Int) = performGetOperation (
        databaseQuery = { localDataSource.getAllPhotos() },
<<<<<<< HEAD
        networkCall = { photoDataSource.getPhotos("waterfall",10,1, API_KEY)}
=======
        networkCall = { photoDataSource.getPhotos(search, perPage, page, API_KEY)},
        saveCallResult = { it.photos?.let { items -> localDataSource.insertAll(items) } }

>>>>>>> 6c7a3dfa417f5a1c79ea51bf36c33e9bdac3a6cb
    )
}
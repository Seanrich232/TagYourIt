package com.example.tagyourit.data.api

import com.example.tagyourit.data.model.PhotoSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET("query")
    suspend fun getPhotos(
        @Query("per_page") per_page: Int,
        @Query("page") page: Int
    ): Response<PhotoSearchResponse>

}
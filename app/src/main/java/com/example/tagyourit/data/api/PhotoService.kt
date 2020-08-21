package com.example.tagyourit.data.api

import com.example.tagyourit.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET("query")
    suspend fun getPhotos(
        @Query("per_page") pageNum: Int,
        @Query("page") count: Int
    ): Response<SearchResponse>
}
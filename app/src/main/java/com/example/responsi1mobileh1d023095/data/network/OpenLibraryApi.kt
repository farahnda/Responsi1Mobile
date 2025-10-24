package com.example.responsi1mobileh1d023095.data.network

import com.example.responsi1mobileh1d023095.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Headers

interface OpenLibraryApi {
    @Headers("X-Auth-Token: 59a4926e98ef489ebf5f5b77b91c1674")
    @GET("teams/524")
    suspend fun getTeamData(): SearchResponse
}
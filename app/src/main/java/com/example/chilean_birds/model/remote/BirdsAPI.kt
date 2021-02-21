package com.example.chilean_birds.model.remote

import retrofit2.Response
import retrofit2.http.GET

interface BirdsAPI {

    @GET("birds")
    suspend fun fetchBirdsData() : Response<List<BirdsData>>
}
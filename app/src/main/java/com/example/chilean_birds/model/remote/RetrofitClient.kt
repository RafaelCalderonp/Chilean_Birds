package com.example.chilean_birds.model.remote

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
        private const val BASE_URL = "https://aves.ninjas.cl/api/"
        fun getRetrofit(): BirdsAPI{
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(BirdsAPI::class.java)
        }
    }


}
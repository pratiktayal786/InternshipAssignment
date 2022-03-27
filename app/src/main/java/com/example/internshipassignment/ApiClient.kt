package com.example.internshipassignment

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private lateinit var apiService: ApiService

    fun getApiService(BASE_URL: String): ApiService{

        if(!::apiService.isInitialized)
        {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService
    }
}
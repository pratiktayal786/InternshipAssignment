package com.example.internshipassignment

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(Constants.POST_URL)
    fun fetchPost(@Header("Authorization") token: String): Call<PostResponse>
}
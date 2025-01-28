package com.example.networkingv2.data.remote

import com.example.networkingv2.data.model.User
import retrofit2.http.GET

interface UserApi {
    @GET("users")
    suspend fun getUsers(): List<User>
}

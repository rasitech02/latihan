package com.example.myapplication.api

import com.example.myapplication.model.UserRequest
import com.example.myapplication.model.UserResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST("login.php")
    fun userLogin(
        @Body userRequest: UserRequest
    ): Call<UserResponse>
}
package com.example.myapplication.api


import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {
    fun getRetrofitClientInstance(): Retrofit {
        val BASE_URL = "https://rantingnupekandangan.com/api/v1/"

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
        return retrofit
    }
}
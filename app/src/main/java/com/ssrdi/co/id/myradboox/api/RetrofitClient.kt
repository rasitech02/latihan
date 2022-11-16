package com.ssrdi.co.id.myradboox.api

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    fun getRetrofitClientInstance() : Retrofit {
        val BASE_URL = "https://api.radboox.com/"

        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(BASE_URL)
            .build()
        return retrofit
    }

}
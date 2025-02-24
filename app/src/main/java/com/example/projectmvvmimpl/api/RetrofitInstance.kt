package com.example.projectmvvmimpl

import com.example.projectmvvmimpl.viewmodel.TaskApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    // Configuration pour émulateur
    private const val BASE_URL = "http://10.0.2.2:3000/"

    val instance: TaskApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TaskApi::class.java)
    }
}
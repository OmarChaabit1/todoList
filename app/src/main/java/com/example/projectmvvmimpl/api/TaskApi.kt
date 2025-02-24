package com.example.projectmvvmimpl.viewmodel

import Task
import retrofit2.http.*

interface TaskApi {
    @GET("tasks")
    suspend fun getAllTasks(): List<Task>

    @POST("tasks")
    suspend fun createTask(@Body task: Task): Task

    @PUT("tasks/{id}")
    suspend fun updateTask(@Path("id") id: String, @Body task: Task): Task

    @DELETE("tasks/{id}")
    suspend fun deleteTask(@Path("id") id: String)
}
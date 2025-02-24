package com.example.projectmvvmimpl
import Task

class TaskRepository {
    private val api = RetrofitClient.instance

    suspend fun getAllTasks() = api.getAllTasks()
    suspend fun addTask(title: String) = api.createTask(Task(title = title))
    suspend fun updateTask(task: Task) = api.updateTask(task.id!!, task)
    suspend fun deleteTask(id: String) = api.deleteTask(id)
}
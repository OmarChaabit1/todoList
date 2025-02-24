package com.example.projectmvvmimpl.viewmodel

import Task
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectmvvmimpl.TaskRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository()

    private val _tasks = MutableStateFlow<List<Task>>(emptyList()) // ✅ Use StateFlow
    val tasks: StateFlow<List<Task>> = _tasks

    private val _errorMessage = MutableStateFlow("")
    val errorMessage: StateFlow<String> = _errorMessage

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            try {
                _tasks.value = repository.getAllTasks()
            } catch (e: Exception) {
                _errorMessage.value = "Error loading tasks: ${e.message}"
            }
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            try {
                val newTask = repository.addTask(title)
                _tasks.value = _tasks.value + newTask // ✅ Update the list properly
            } catch (e: Exception) {
                _errorMessage.value = "Error adding task: ${e.message}"
            }
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            try {
                val updatedTask = repository.updateTask(task)
                _tasks.value = _tasks.value.map { if (it.id == updatedTask.id) updatedTask else it }
            } catch (e: Exception) {
                _errorMessage.value = "Error updating task: ${e.message}"
            }
        }
    }

    fun deleteTask(task: Task) {
        viewModelScope.launch {
            try {
                repository.deleteTask(task.id!!)
                _tasks.value = _tasks.value.filter { it.id != task.id }
            } catch (e: Exception) {
                _errorMessage.value = "Error deleting task: ${e.message}"
            }
        }
    }
}

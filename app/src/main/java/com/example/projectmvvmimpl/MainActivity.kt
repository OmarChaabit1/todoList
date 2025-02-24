package com.example.projectmvvmimpl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*

import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.projectmvvmimpl.ui.components.AddTaskDialog
import com.example.projectmvvmimpl.ui.components.TaskList
//import com.example.projectmvvmimpl.ui.theme.Primary
import com.example.projectmvvmimpl.ui.theme.ProjectMvvmImplTheme
import com.example.projectmvvmimpl.viewmodel.TaskViewModel
import com.example.projectmvvmimpl.viewmodel.*
import androidx.compose.runtime.collectAsState
import com.example.projectmvvmimpl.ui.components.EmptyState

class MainActivity : ComponentActivity() {
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProjectMvvmImplTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    TaskListScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun TaskListScreen(viewModel: TaskViewModel) {
    val tasks by viewModel.tasks.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ) {
                Icon(
                    Icons.Filled.Add,
                    "Add Task",
                    tint = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            if (tasks.isEmpty()) {
                EmptyState()
            } else {
                TaskList(
                    tasks = tasks,
                    onDeleteTask = viewModel::deleteTask,
                    onUpdateTask = viewModel::updateTask,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(15.dp)
                        .animateContentSize()
                )
            }
        }
    }

    if (showAddDialog) {
        AddTaskDialog(
            onAddTask = { task ->
                viewModel.addTask(task)
                showAddDialog = false
            },
            onDismiss = { showAddDialog = false }
        )
    }
}

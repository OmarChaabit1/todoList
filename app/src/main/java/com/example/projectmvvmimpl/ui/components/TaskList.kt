package com.example.projectmvvmimpl.ui.components

import Task
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskList(
    tasks: List<Task>, onDeleteTask: (Task) -> Unit, onUpdateTask: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier
    ) {
        items(items = tasks, key = { it.id ?: "" }) { task ->
            TaskItem(task = task, onDelete = onDeleteTask, onUpdate = onUpdateTask)
            Spacer(modifier = Modifier.height(8.dp))
        }

    }
}
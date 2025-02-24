package com.example.projectmvvmimpl.ui.components

import androidx.compose.runtime.Composable
import Task

@Composable
fun EditTaskDialog(
    task: Task,
    onUpdate: (Task) -> Unit,
    onDismiss: () -> Unit
) {
    TaskDialog(
        dialogTitle = "Edit Task #${task.id}",
        initialValue = task.title,
        label = "Task",
        placeholder = "Update task name...",
        onSave = { updatedTitle -> onUpdate(task.copy(title = updatedTitle)) },
        onDismiss = onDismiss
    )
}


package com.example.projectmvvmimpl.ui.components

import androidx.compose.runtime.*

@Composable
fun AddTaskDialog(onAddTask: (String) -> Unit, onDismiss: () -> Unit) {
    TaskDialog(
        dialogTitle = "New Task",
        initialValue = "",
        label = "Task",
        placeholder = "Enter task...",
        onSave = { task -> onAddTask(task) },
        onDismiss = onDismiss
    )
}

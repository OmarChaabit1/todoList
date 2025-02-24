package com.example.projectmvvmimpl.ui.components

import androidx.compose.foundation.layout.*

import androidx.compose.runtime.*
import androidx.compose.foundation.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*

import androidx.compose.ui.*
import androidx.compose.ui.unit.dp

@Composable
fun TaskInput(
    value: String,
    onValueChange: (String) -> Unit,
    onSubmit: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.weight(1f),
            placeholder = { Text("Add new task") },
            singleLine = true
        )

        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Task",
            modifier = Modifier
                .clickable(onClick = onSubmit)
                .padding(start = 8.dp)
                .size(48.dp)
        )
    }
}
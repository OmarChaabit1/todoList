package com.example.projectmvvmimpl.ui.components

import Task
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*

import androidx.compose.ui.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.style.TextDecoration

import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
@Composable
fun TaskItem(
    task: Task,
    onUpdate: (Task) -> Unit,
    onDelete: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    var showEditDialog by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = MaterialTheme.colorScheme.surface) // Use surface color
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = { onUpdate(task.copy(isCompleted = it)) },
                colors = CheckboxDefaults.colors(
                    checkedColor = MaterialTheme.colorScheme.primary, // Use primary color for checked state
                    uncheckedColor = MaterialTheme.colorScheme.onSurfaceVariant // Use variant color for unchecked state
                )
            )
            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = task.title,
                color = if (task.isCompleted) MaterialTheme.colorScheme.onSurfaceVariant else LocalContentColor.current, // Use theme color
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
                textDecoration = if (task.isCompleted) TextDecoration.LineThrough else null
            )

            IconButton(onClick = { showEditDialog = true }) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit",
                    tint = MaterialTheme.colorScheme.onSurface // Use theme color for edit icon
                )
            }

            IconButton(onClick = { showDeleteConfirmation = true }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = MaterialTheme.colorScheme.error // Use theme's error color for delete icon
                )
            }
        }
    }

    if (showEditDialog) {
        EditTaskDialog(
            task = task,
            onUpdate = { updated ->
                onUpdate(updated)
                showEditDialog = false
            },
            onDismiss = { showEditDialog = false }
        )
    }

    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            title = { Text("Confirm Delete") },
            text = { Text("Are you sure you want to delete this task?") },
            confirmButton = {
                TextButton(onClick = {
                    onDelete(task)
                    showDeleteConfirmation = false
                }) {
                    Text("Delete", color = MaterialTheme.colorScheme.error) // Use error color for delete confirmation
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteConfirmation = false },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                ) {
                    Text("Cancel")
                }
            },
            containerColor = MaterialTheme.colorScheme.surface // Use surface color for dialog background
        )
    }
}

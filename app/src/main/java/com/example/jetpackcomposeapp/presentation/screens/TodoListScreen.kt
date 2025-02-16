package com.example.jetpackcomposeapp.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposeapp.domain.model.TodoModel
import com.example.jetpackcomposeapp.domain.model.initializeDummyData
import com.example.jetpackcomposeapp.presentation.components.TodoItemComponent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen() {
    var todoList by remember { mutableStateOf(initializeDummyData()) }
    var newTodo by remember { mutableStateOf("") }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(12.dp))
            .padding(24.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(value = newTodo, onValueChange = { newTodo = it }, label = {
                Text(text = "New to do")
            })
            IconButton(
                onClick = {
                    todoList = todoList + TodoModel(
                        id = todoList.size + 1, title = newTodo, isCompleted = false
                    )
                    newTodo = ""

                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }

        todoList.forEach { todo ->
            TodoItemComponent(todo = todo, onToggle = {
                todoList = todoList.map {
                    if (it.id == todo.id) it.copy(isCompleted = !it.isCompleted) else it
                }
            }, onDelete = {
                todoList = todoList.filter { it.id != todo.id }
            })
        }


    }
}
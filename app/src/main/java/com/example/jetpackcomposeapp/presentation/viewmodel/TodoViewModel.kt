package com.example.jetpackcomposeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeapp.domain.model.TodoModel
import com.example.jetpackcomposeapp.domain.repository.TodoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    val todoList: StateFlow<List<TodoModel>> = repository.todoList.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.fetchTodosFromApiAndSaveToDatabase()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addTodo(title: String) {
        if (title.isNotBlank()) {
            val newTodo =
                TodoModel(id = todoList.value.size + 1, title = title, isCompleted = false)
            viewModelScope.launch {
                repository.insertTodo(newTodo)
            }
        }
    }

    fun toggleTodo(todo: TodoModel) {
        viewModelScope.launch {
            repository.updateTodo(todo.copy(isCompleted = !todo.isCompleted))
        }
    }

    fun deleteTodo(todo: TodoModel) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }
}

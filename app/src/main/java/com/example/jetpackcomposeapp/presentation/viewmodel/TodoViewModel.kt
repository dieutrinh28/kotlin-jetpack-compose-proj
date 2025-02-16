package com.example.jetpackcomposeapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposeapp.domain.repository.TodoRepository
import kotlinx.coroutines.launch


class TodoViewModel(
    private val repository: TodoRepository
) : ViewModel() {

    fun getAllMockTodos() {
        viewModelScope.launch {
            val todos = repository.getAllMockTodos()
        }
    }

    fun fetchTodos() {
        viewModelScope.launch {
            val todos = repository.getAllTodos()
        }
    }
}

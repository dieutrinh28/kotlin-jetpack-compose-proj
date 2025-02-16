package com.example.jetpackcomposeapp.domain.repository

import com.example.jetpackcomposeapp.data.local.dao.TodoDao
import com.example.jetpackcomposeapp.data.local.entity.TodoEntity
import com.example.jetpackcomposeapp.data.remote.api.TodoApiService
import com.example.jetpackcomposeapp.domain.model.TodoModel
import com.example.jetpackcomposeapp.domain.model.initializeDummyData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepository (
    private val todoDao: TodoDao,
    private val apiService: TodoApiService
) {
    fun getAllMockTodos(): List<TodoModel> {
        return initializeDummyData()
    }

    // get todos from database - TodoEntity and convert to TodoModel
    fun getAllTodos(): Flow<List<TodoModel>> {
        return todoDao.getAllTodos().map { entities ->
            entities.map { entity -> TodoModel(entity.id, entity.title, entity.isCompleted) }
        }
    }

    // get todos from API - TodoResponse and convert to TodoModel
    suspend fun fetchTodosFromApi(): List<TodoModel> {
        return apiService.getTodos().map { response ->
            TodoModel(response.id, response.title, response.isCompleted)
        }
    }

    // add todo to database
    suspend fun insertTodo(todoModel: TodoModel) {
        todoDao.insertTodo(TodoEntity(todoModel.id, todoModel.title, todoModel.isCompleted))
    }

    // update todo
    suspend fun updateTodo(todoModel: TodoModel) {
        todoDao.updateTodo(TodoEntity(todoModel.id, todoModel.title, todoModel.isCompleted))
    }

    // delete todo
    suspend fun deleteTodo(todoModel: TodoModel) {
        todoDao.deleteTodo(TodoEntity(todoModel.id, todoModel.title, todoModel.isCompleted))
    }

    // delete all todos
    suspend fun deleteAllTodos(todoModel: TodoModel) {
        todoDao.deleteAllTodos()
    }
}
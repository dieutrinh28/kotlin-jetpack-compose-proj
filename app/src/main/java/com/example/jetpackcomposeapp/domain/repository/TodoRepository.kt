package com.example.jetpackcomposeapp.domain.repository

import com.example.jetpackcomposeapp.data.local.dao.TodoDao
import com.example.jetpackcomposeapp.data.local.entity.TodoEntity
import com.example.jetpackcomposeapp.data.remote.api.TodoApiService
import com.example.jetpackcomposeapp.data.remote.model.TodoResponse
import com.example.jetpackcomposeapp.domain.model.TodoModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodoRepository(
    private val todoDao: TodoDao,
    private val apiService: TodoApiService
) {

    val todoList: Flow<List<TodoModel>> = getAllTodos()

    // get todos from Room Database
    private fun getAllTodos(): Flow<List<TodoModel>> {
        return todoDao.getAllTodos().map { entities ->
            entities.map { entity -> TodoModel(entity.id, entity.title, entity.isCompleted) }
        }
    }

    // fetch todos from API and save to Room Database
    suspend fun fetchTodosFromApiAndSaveToDatabase() {
        try {
            val todos = apiService.getTodos().map { response ->
                TodoModel(response.id, response.title, response.isCompleted)
            }
            val todosEntity = todos.map { entity ->
                TodoEntity(
                    entity.id, entity.title, entity.isCompleted
                )
            }
            todoDao.insertTodos(todosEntity)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // send a request to add new task via API, then save it to Room Database if successful
    suspend fun insertTodo(todoModel: TodoModel) {
        try {
            val response = apiService.addTodo(
                TodoResponse(
                    todoModel.id,
                    todoModel.title,
                    todoModel.isCompleted
                )
            )
            if (response.isSuccessful) {
                response.body()?.let { savedTodo ->
                    val entity = TodoEntity(savedTodo.id, savedTodo.title, savedTodo.isCompleted)
                    todoDao.insertTodo(entity)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // send an update request to API, then update the task in Room Database if successful
    suspend fun updateTodo(todoModel: TodoModel) {
        try {
            val response = apiService.updateTodo(
                todoModel.id, TodoResponse(
                    todoModel.id,
                    todoModel.title,
                    todoModel.isCompleted
                )
            )
            if (response.isSuccessful) {
                val entity = TodoEntity(todoModel.id, todoModel.title, todoModel.isCompleted)
                todoDao.updateTodo(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // send a delete request to API, then remove the task from Room Database if successful
    suspend fun deleteTodo(todoModel: TodoModel) {
        try {
            val response = apiService.deleteTodo(
                todoModel.id
            )
            if (response.isSuccessful) {
                val entity = TodoEntity(todoModel.id, todoModel.title, todoModel.isCompleted)
                todoDao.deleteTodo(entity)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}
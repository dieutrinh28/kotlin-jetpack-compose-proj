package com.example.jetpackcomposeapp.data.remote.api

import com.example.jetpackcomposeapp.data.remote.model.TodoResponse
import retrofit2.http.*

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<TodoResponse>

    @POST("todos")
    suspend fun addTodo(@Body todoResponse: TodoResponse): TodoResponse

    @DELETE("todos/{id}")
    suspend fun deleteTodo(@Path("id") todoId: Int): Unit
}
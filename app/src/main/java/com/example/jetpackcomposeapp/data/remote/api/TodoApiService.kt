package com.example.jetpackcomposeapp.data.remote.api

import com.example.jetpackcomposeapp.data.remote.model.TodoResponse
import retrofit2.Response
import retrofit2.http.*

interface TodoApiService {
    @GET("todos")
    suspend fun getTodos(): List<TodoResponse>

    @POST("todos/add")
    suspend fun addTodo(@Body todoResponse: TodoResponse): Response<TodoResponse>

    @PUT("todos/{id}")
    suspend fun updateTodo(
        @Path("id") todoId: Int,
        @Body todoResponse: TodoResponse
    ): Response<TodoResponse>

    @DELETE("todos/{id}")
    suspend fun deleteTodo(@Path("id") todoId: Int): Response<Unit>
}
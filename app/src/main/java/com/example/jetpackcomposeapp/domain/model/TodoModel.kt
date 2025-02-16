package com.example.jetpackcomposeapp.domain.model

data class TodoModel(
    val id: Int = 0,
    val title: String,
    val isCompleted: Boolean
)

fun initializeDummyData(): List<TodoModel> {
    return listOf(
        TodoModel(1, "Create model", false),
        TodoModel(2, "Create repository", false),
        TodoModel(3, "Create view model", false),
        TodoModel(4, "Create todo component", false),
        TodoModel(5, "Create todo list screen", false),
        TodoModel(6, "Create add todo screen", false),
        TodoModel(7, "Create remove todo function", false),
    )
}
package com.example.jetpackcomposeapp.model

data class TodoItemModel(val id: Int, val title: String, val isCompleted: Boolean)

fun initializeDummyData(): List<TodoItemModel> {
    return listOf(
        TodoItemModel(1, "Create model", false),
        TodoItemModel(2, "Create repository", false),
        TodoItemModel(3, "Create view model", false),
        TodoItemModel(4, "Create todo component", false),
        TodoItemModel(5, "Create todo list screen", false),
        TodoItemModel(6, "Create add todo screen", false),
        TodoItemModel(7, "Create remove todo function", false),
    )
}
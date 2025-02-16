package com.example.jetpackcomposeapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.jetpackcomposeapp.domain.model.TodoModel

@Entity(tableName = "todo_table")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val isCompleted: Boolean
) {
    // convert from entity to domain model
    fun toDomain(): TodoModel {
        return TodoModel(id, title, isCompleted)
    }

    companion object {
        // convert from domain model to entity
        fun fromDomain(todo: TodoModel): TodoEntity {
            return TodoEntity(todo.id, todo.title, todo.isCompleted)
        }
    }
}
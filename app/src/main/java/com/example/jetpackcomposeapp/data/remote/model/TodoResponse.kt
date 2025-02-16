package com.example.jetpackcomposeapp.data.remote.model

import com.google.gson.annotations.SerializedName

data class TodoResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("isCompleted") val isCompleted: Boolean,
)
package com.example.jetpackcomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposeapp.data.local.TodoDatabase
import com.example.jetpackcomposeapp.data.remote.RetrofitInstance
import com.example.jetpackcomposeapp.domain.repository.TodoRepository
import com.example.jetpackcomposeapp.routing.AppNavigation
import com.example.jetpackcomposeapp.presentation.theme.JetpackComposeAppTheme
import com.example.jetpackcomposeapp.presentation.viewmodel.TodoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = TodoDatabase.getInstance(this)
        val dao = database.todoDao()

        val apiService = RetrofitInstance.api

        val repository = TodoRepository(dao, apiService)

        val viewModel = TodoViewModel(repository)

        setContent {
            JetpackComposeAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(viewModel)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    JetpackComposeAppTheme {}
}
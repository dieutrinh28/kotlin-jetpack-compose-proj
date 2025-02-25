package com.example.jetpackcomposeapp.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeapp.presentation.screens.LoginScreen
import com.example.jetpackcomposeapp.presentation.screens.TodoListScreen
import com.example.jetpackcomposeapp.presentation.viewmodel.TodoViewModel

@Composable
fun AppNavigation(viewModel: TodoViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RouteConstants.LOGIN) {
        composable(RouteConstants.LOGIN) {
            LoginScreen(navController)
        }
        composable(RouteConstants.TODO_LIST) {
            TodoListScreen(viewModel)
        }
    }
}
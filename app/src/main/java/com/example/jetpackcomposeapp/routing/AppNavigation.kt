package com.example.jetpackcomposeapp.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeapp.ui.screens.LoginScreen
import com.example.jetpackcomposeapp.ui.screens.TodoListScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = RouteConstants.LOGIN) {
        composable(RouteConstants.LOGIN) {
            LoginScreen(navController)
        }
        composable(RouteConstants.TODO_LIST) {
            TodoListScreen()
        }
    }
}
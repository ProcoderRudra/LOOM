package com.loom.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.loom.ui.screens.login.LoginScreen
import com.loom.ui.screens.welcome.WelcomeScreen

object Route {
    const val WELCOME = "welcome"
    const val LOGIN = "login"
    const val HOME = "home"
}

@Composable
fun LoomNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Route.WELCOME
    ) {
        composable(Route.WELCOME) {
            WelcomeScreen(
                onLoginClick = { navController.navigate(Route.LOGIN) },
                onSignUpClick = { /* TODO: Navigate to SignUp */ }
            )
        }
        composable(Route.LOGIN) {
            LoginScreen(
                onLoginSuccess = { navController.navigate(Route.HOME) },
                onSignUpClick = { /* TODO: Navigate to SignUp */ },
                onForgotPasswordClick = { /* TODO: Navigate to ForgotPassword */ }
            )
        }
    }
}

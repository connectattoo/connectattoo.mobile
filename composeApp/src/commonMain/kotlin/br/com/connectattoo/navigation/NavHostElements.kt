package br.com.connectattoo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.connectattoo.screen_app.account_manager.loginScreen.LoginScreen
import br.com.connectattoo.screen_app.screens_apresentation.splashScreen.SplashScreen


@Composable
fun NavHostAccountManager() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("login") { LoginScreen(navController) }
    }
}



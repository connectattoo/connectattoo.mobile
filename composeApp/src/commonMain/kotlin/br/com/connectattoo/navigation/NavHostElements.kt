package br.com.connectattoo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.connectattoo.ui.screen_app.account_manager.accountConfirmation.AccountConfirmationScreen
import br.com.connectattoo.ui.screen_app.account_manager.loginScreen.LoginScreen
import br.com.connectattoo.ui.screen_app.screens_apresentation.splashScreen.SplashScreen
import br.com.connectattoo.ui.screen_app.screens_apresentation.welcomepage.WelcomePage
import br.com.connectattoo.ui.screen_app.account_manager.registerScreen.RegisterScreen
import br.com.connectattoo.ui.screen_app.account_manager.registerScreenTattooArtist.RegisterTattooArtistScreen


@Composable
fun Presentation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "registerTattooArtist") {
        composable("splash") { SplashScreen(navController) }
        composable("welcomepage") { WelcomePage(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("registerTattooArtist") { RegisterTattooArtistScreen(navController) }
        composable("account_confirmation") { AccountConfirmationScreen(navController) }
        composable("account_manager") { AccountManager() }
    }
}
@Composable
fun NavHostAccountManager() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "register") {
        composable("login") { LoginScreen(navController) }


    }
}



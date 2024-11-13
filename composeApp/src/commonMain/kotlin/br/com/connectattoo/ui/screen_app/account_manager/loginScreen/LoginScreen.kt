package br.com.connectattoo.ui.screen_app.account_manager.loginScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.navigation.NavController
import br.com.connectattoo.ui.screen_app.account_manager.loginScreen.components.Screen
import br.com.connectattoo.ui.screen_app.account_manager.registerScreen.FakeRegisterViewModel
import br.com.connectattoo.ui.screen_app.account_manager.registerScreen.RegisterViewModel
import br.com.connectattoo.ui.screen_app.account_manager.registerScreen.getRegisterViewModelForPreview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI


@OptIn(KoinExperimentalAPI::class)
@Composable
fun getLoginViewModelForPreview(): LoginViewModel {
    return if (LocalInspectionMode.current) {
        FakeLoginViewModel()
    }else{
        koinViewModel()
    }
}

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = getLoginViewModelForPreview()
    Screen(navController, viewModel = viewModel)
}
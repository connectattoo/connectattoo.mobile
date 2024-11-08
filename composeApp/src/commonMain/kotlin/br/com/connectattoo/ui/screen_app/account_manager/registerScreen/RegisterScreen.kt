package br.com.connectattoo.ui.screen_app.account_manager.registerScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.navigation.NavController
import br.com.connectattoo.ui.screen_app.account_manager.registerScreen.components.Screen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun getRegisterViewModelForPreview(): RegisterViewModel {
    return if (LocalInspectionMode.current) {
        FakeRegisterViewModel()
    }else{
        koinViewModel()
    }
}

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel: RegisterViewModel = getRegisterViewModelForPreview()

    //val context = LocalContext.current

    /*LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    navController.navigate("login")
                }

                is ValidationEvent.Failed -> {
                    //Toast.makeText(context, viewModel.message.value, Toast.LENGTH_LONG).show()
                }
            }
        }
    }*/
    Screen( navController, viewModel)
}
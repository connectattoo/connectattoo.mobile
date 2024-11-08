package br.com.connectattoo.screen_app.account_manager.registerScreen

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import br.com.connectattoo.screen_app.account_manager.registerScreen.components.Screen

@Composable
fun getBirthDateViewModelForPreview(): RegisterViewModel {
    return FakeRegisterViewModel()
}

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel: RegisterViewModel = getBirthDateViewModelForPreview()

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
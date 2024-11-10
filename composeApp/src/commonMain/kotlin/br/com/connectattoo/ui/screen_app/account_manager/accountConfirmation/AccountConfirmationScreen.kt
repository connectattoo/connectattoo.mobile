package br.com.connectattoo.ui.screen_app.account_manager.accountConfirmation

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.navigation.NavController
import br.com.connectattoo.ui.screen_app.account_manager.accountConfirmation.components.Screen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun getAccountViewModelForPreview(): AccountConfirmationViewModel {
    return if (LocalInspectionMode.current) {
        FakeAccountConfirmationViewModel()
    }else{
        koinViewModel()
    }
}

@Composable
fun AccountConfirmationScreen(navController: NavController) {
    val viewModel: AccountConfirmationViewModel = getAccountViewModelForPreview()
    val snackbarHostState = remember { SnackbarHostState() }

   /* LaunchedEffect(key1 = viewModel) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    
                    navController.navigate("login")
                }

                is ValidationEvent.Failed -> {
                    snackbarHostState.showSnackbar(
                        message = viewModel.message.value,
                        duration = SnackbarDuration.Long
                    )
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
    }*/
    Screen(navController, viewModel)
}

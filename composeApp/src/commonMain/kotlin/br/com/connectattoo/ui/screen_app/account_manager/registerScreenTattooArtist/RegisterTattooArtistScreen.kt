package br.com.connectattoo.ui.screen_app.account_manager.registerScreenTattooArtist

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.navigation.NavController
import br.com.connectattoo.ui.screen_app.account_manager.registerScreenTattooArtist.components.Screen
import br.com.connectattoo.util.ValidationEvent
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun getRegisterViewModelForPreview(): RegisterTattooArtistViewModel {
    return if (LocalInspectionMode.current) {
        FakeRegisterTattooArtistViewModel()
    }else{
        koinViewModel()
    }
}

@Composable
fun RegisterTattooArtistScreen(navController: NavController) {
    val viewModel: RegisterTattooArtistViewModel = getRegisterViewModelForPreview()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = viewModel) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {

                    navController.navigate("account_confirmation")
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
        Screen(navController, viewModel, paddingValues)
    }
}
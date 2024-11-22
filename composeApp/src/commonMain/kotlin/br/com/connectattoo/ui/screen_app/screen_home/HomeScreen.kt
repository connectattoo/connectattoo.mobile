package br.com.connectattoo.ui.screen_app.screen_home

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import br.com.connectattoo.ui.screen_app.screen_home.components.Screen
import br.com.connectattoo.util.ValidationEvent
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeViewModel = koinViewModel()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(key1 = viewModel) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {

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

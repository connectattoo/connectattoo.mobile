package br.com.connectattoo.ui.screen_app.screens_apresentation.splashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import br.com.connectattoo.ui.components.ImageLogo
import br.com.connectattoo.util.ValidationEvent
import connectattoo.composeapp.generated.resources.Res
import connectattoo.composeapp.generated.resources.image_splash
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SplashScreen(navController: NavHostController) {
    val viewModel: SplashViewModel = koinViewModel()
    LaunchedEffect(key1 = viewModel) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    navController.navigate("mainContent")
                }

                is ValidationEvent.Failed -> {
                    when (viewModel.message.value) {
                        "Usuário não foi confirmado." -> {
                            navController.navigate("account_confirmation")

                        }

                        else -> navController.navigate("welcomepage")
                    }

                }
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets(0))

    ) {
        Image(
            painter = painterResource(Res.drawable.image_splash),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFdab6ff).copy(alpha = 0.6f),
                            Color(0xFFe4e4e4).copy(alpha = 0.6f)
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY)
                    )
                )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.sdp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageLogo()
        }
    }
}
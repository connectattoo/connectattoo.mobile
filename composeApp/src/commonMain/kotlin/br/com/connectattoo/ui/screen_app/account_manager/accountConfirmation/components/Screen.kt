package br.com.connectattoo.ui.screen_app.account_manager.accountConfirmation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavController
import br.com.connectattoo.ui.components.ImageLogo
import br.com.connectattoo.ui.screen_app.account_manager.accountConfirmation.AccountConfirmationViewModel
import br.com.connectattoo.ui.theme.extendedColors
import connectattoo.composeapp.generated.resources.Res
import connectattoo.composeapp.generated.resources.bird_account_confirmation
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource

@Composable
fun Screen(
    navController: NavController,
    viewModel: AccountConfirmationViewModel
) {
    val isRefreshing = remember { mutableStateOf(false) }

    LaunchedEffect(isRefreshing.value) {
        if (isRefreshing.value) {

            kotlinx.coroutines.delay(1500)
            viewModel.getToken()
            isRefreshing.value = false
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onVerticalDrag = { _, dragAmount ->
                        if (dragAmount > 0) {
                            isRefreshing.value = true
                        }
                    }
                )
            }
    ) {
        Row(modifier = Modifier.padding(top = 16.sdp)) {

            ImageLogo()
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.sdp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(Res.drawable.bird_account_confirmation),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(90.sdp)
            )

            Spacer(modifier = Modifier.padding(15.sdp))

            Text(
                text = "SUA CONTA ESTA QUASE PRONTA!",
                fontSize = 25.ssp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.extendedColors.purple500,
                lineHeight = 25.ssp,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.padding(15.sdp))

            Text(
                text = buildAnnotatedString {
                    append("Verifique seu ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("e-mail") }
                    append(" e confirme sua conta para desbloquear todas as funcionalidades do ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Connectattoo.") }
                },
                fontSize = 14.ssp,
                lineHeight = 20.ssp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
        if (isRefreshing.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.sdp)
            )
        } else {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("Deslizar") }
                    append(" para baixo, para ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) { append("atualizar.") }
                },
                fontSize = 14.ssp,
                lineHeight = 20.ssp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.sdp)
            )
        }
    }
}

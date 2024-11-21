package br.com.connectattoo.ui.screen_app.screen_home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import connectattoo.composeapp.generated.resources.Res
import connectattoo.composeapp.generated.resources.SIMBOLO
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource


@Composable
fun Header(
    modifier: Modifier = Modifier,
    name: String = "João",
) {
    Row(modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        ) {

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Olá $name, ")
                }

                append("vamos encontrara \nsua tatuagem perfeita!")
            },
            fontSize = 13.ssp,
            minLines = 2,
            lineHeight = 15.ssp,
            textAlign = TextAlign.Start,
            modifier = Modifier.weight((1f)).align(Alignment.CenterVertically)
        )
        Spacer(modifier = Modifier.padding(end = 8.sdp))
        val image: Painter = painterResource(Res.drawable.SIMBOLO)

        Image(
            modifier = Modifier.size(50.sdp).weight(0.2f),
            painter = image,
            contentDescription = "Imagem logo",
        )

    }

}
package br.com.connectattoo.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import connectattoo.composeapp.generated.resources.Res
import connectattoo.composeapp.generated.resources.logo_positivo
import org.jetbrains.compose.resources.painterResource


@Composable
fun ImageLogo(
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .size(50.dp)
) {
    val image: Painter = painterResource(Res.drawable.logo_positivo)

    Image(
        painter = image,
        contentDescription = "Imagem logo",
        modifier = modifier
    )
}



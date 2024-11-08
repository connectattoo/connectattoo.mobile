package br.com.connectattoo.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

@Composable
fun ButtonBackgroundPurple(
    submit: () -> Unit,
    enableButton: Boolean,
    modifier: Modifier = Modifier,
    text: String = "Button",
    buttonColor: ButtonColors = ButtonDefaults.buttonColors(androidx.compose.material.MaterialTheme.colors.primary),
    textColor: Color = Color.White,
    isLoading: Boolean = false
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        androidx.compose.material3.Button(
            onClick = { submit() },
            enabled = enableButton,
            modifier = modifier.then(Modifier.size(40.sdp)).fillMaxSize(),
            shape = CircleShape,
            border = BorderStroke(
                width = 2.sdp,
                color = androidx.compose.material.MaterialTheme.colors.primary,
            ),

            colors = buttonColor,

            contentPadding = PaddingValues(10.sdp)
        ) {
            if (!isLoading) {
                Text(
                    text = text,
                    fontWeight = FontWeight.W900,
                    fontSize = 12.ssp,
                    style = MaterialTheme.typography.titleLarge,
                    color = textColor
                )
            } else {
                CircularProgressIndicator(
                    modifier = Modifier.size(17.sdp),
                    color = textColor
                )
            }
        }
    }
}

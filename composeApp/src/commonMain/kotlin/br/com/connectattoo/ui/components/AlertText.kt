package br.com.connectattoo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import network.chaintech.sdpcomposemultiplatform.ssp

@Composable
fun AlertText(modifier: Modifier = Modifier.fillMaxWidth(), textMessage: String?) {
    if (textMessage != null)
        Text(
            fontSize = 12.ssp,
            text = textMessage,
            modifier = modifier,
            color = MaterialTheme.colorScheme.error
        )
}

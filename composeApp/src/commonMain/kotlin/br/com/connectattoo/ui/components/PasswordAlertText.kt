package br.com.connectattoo.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import br.com.connectattoo.ui.theme.extendedColors
import connectattoo.composeapp.generated.resources.Res
import connectattoo.composeapp.generated.resources.check_off
import connectattoo.composeapp.generated.resources.check_ok
import connectattoo.composeapp.generated.resources.eye_visibility_off
import connectattoo.composeapp.generated.resources.eye_visibility_on
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource

@Composable
fun PasswordAlertText(modifier: Modifier = Modifier.fillMaxWidth(), textMessage: String?, textColor: Color?) {
    if (textMessage != null)
        Row(modifier  = modifier,) {
            val iconResource =
                if (textColor == MaterialTheme.extendedColors.green) Res.drawable.check_ok else Res.drawable.check_off
            if (textColor != null) {
                Icon(
                    painter = painterResource(iconResource),
                    contentDescription = null,
                    tint = textColor,
                    modifier = Modifier.size(12.sdp).align(Alignment.CenterVertically)
                )
            }
            Spacer(modifier = Modifier.width(4.sdp))
            Text(
                fontSize = 12.ssp,
                text = textMessage,
                color = textColor ?: MaterialTheme.extendedColors.green
            )
        }
}

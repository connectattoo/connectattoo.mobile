package br.com.connectattoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

@Composable
fun DateInputText(
    modifier: Modifier = Modifier,
    textInputModifier: Modifier = Modifier,
    placeholderText: String = "Placeholder",
    titleText: String = "Title",
    textValue: String,
    isError: Boolean = false,
    textError: List<String>? = null,
    onEvent: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val colorBorder = MaterialTheme.colorScheme.outline
    var inFocus by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Row {
            Text(
                text = titleText,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.ssp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.sdp, bottom = 5.sdp)
            )
        }
        Row {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    modifier = textInputModifier
                        .padding(5.sdp)
                        .height(40.sdp)
                        .background(Color.White)
                        .onFocusChanged {
                            inFocus = if (it.hasFocus)
                                it.hasFocus
                            else {
                                it.hasFocus
                            }
                        }.drawBehind {
                            val stroke = Stroke(
                                width = 1.dp.toPx(),
                            )
                            drawRoundRect(
                                color = if (isError) Color.Transparent else colorBorder,
                                style = stroke,
                                cornerRadius = CornerRadius(5.dp.toPx())
                            )

                        }
                        .border(
                            2.dp,
                            if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.background,
                            shape = RoundedCornerShape(5.sdp)
                        )
                        .clip(RoundedCornerShape(5.sdp)),

                    value = textValue,
                    onValueChange = { newValue ->
                        if (newValue.length <= 8) {
                            onEvent(newValue)
                        }
                    },
                    textStyle = TextStyle(
                        fontSize = 14.ssp,
                        color = MaterialTheme.colorScheme.onSurface
                    ),
                    placeholder = { Text(text = placeholderText) },
                    maxLines = 1,
                    visualTransformation = visualTransformation,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )


            }

        }
        Row(modifier = Modifier.fillMaxWidth()) {
            textError?.forEach {
                AlertText(textMessage = it, modifier = Modifier.padding(2.sdp))
            }
        }
    }
}




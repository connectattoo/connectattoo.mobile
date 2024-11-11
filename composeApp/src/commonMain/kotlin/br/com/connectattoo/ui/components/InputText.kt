package br.com.connectattoo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.connectattoo.ui.theme.extendedColors
import connectattoo.composeapp.generated.resources.Res
import connectattoo.composeapp.generated.resources.eye_visibility_off
import connectattoo.composeapp.generated.resources.eye_visibility_on
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource

@Composable
fun InputText(
    modifier: Modifier = Modifier,
    textInputModifier: Modifier = Modifier,
    placeholderText: String = "Placeholder",
    titleText: String = "Title",
    textValue: String,
    isPassword: Boolean = false,
    isError: Boolean = false,
    textError: List<String>? = null,
    onEvent: (String) -> Unit,
    hasAMask: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text),
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    var showPassword by remember { mutableStateOf(false) }
    val colorBorder = MaterialTheme.colorScheme.outline
    val colorBorderGreen = MaterialTheme.extendedColors.green

    Column(modifier = modifier) {
        Row {
            Text(
                text = titleText,
                textAlign = TextAlign.Start,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 12.ssp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.sdp, bottom = 5.sdp, top = 5.sdp)
            )
        }

        Row {
            BasicTextField(
                modifier = textInputModifier
                    .fillMaxWidth()
                    .testTag("InputField_test")
                    .padding(5.sdp)
                    .height(45.sdp)
                    .drawBehind {
                        val stroke = Stroke(
                            width = 1.dp.toPx(),
                        )
                        drawRoundRect(
                            color = if (isError) Color.Transparent else if (!isError && textValue.isNotEmpty()) Color.Transparent else colorBorder,
                            style = stroke,
                            cornerRadius = CornerRadius(5.dp.toPx())
                        )

                    }
                    .border(
                        1.dp,
                        if (!isError && textValue.isNotEmpty()) MaterialTheme.extendedColors.green else if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(5.sdp)
                    )
                    .clip(RoundedCornerShape(5.sdp)),
                value = textValue,
                onValueChange = { text -> onEvent(text) },
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 12.ssp,
                    color = if (isSystemInDarkTheme()) Color.Black else MaterialTheme.colorScheme.onSurface
                ),
                maxLines = 1,
                visualTransformation =
                if (isPassword) {
                    if (showPassword) VisualTransformation.None
                    else PasswordVisualTransformation()
                } else visualTransformation,
                keyboardOptions = keyboardOptions,
                decorationBox = {
                    Row(
                        modifier = Modifier
                            .background(Color.White)
                            .padding(start = 12.sdp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {
                            if (textValue.isEmpty() && !hasAMask) {
                                Text(
                                    modifier = Modifier,
                                    text = placeholderText,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.outline,
                                    fontSize = 12.ssp
                                )
                            }
                            it()
                        }
                        if (isPassword) {
                            val iconResource =
                                if (showPassword) Res.drawable.eye_visibility_on else Res.drawable.eye_visibility_off
                            val contentDescription =
                                if (showPassword) "Ocultar senha" else "Mostrar senha"

                            IconButton(onClick = { showPassword = !showPassword }) {
                                Icon(
                                    painter = painterResource(iconResource),
                                    contentDescription = contentDescription,
                                    tint = MaterialTheme.colorScheme.outline
                                )
                            }
                        }
                    }
                }
            )
        }
    }
    if (textError != null && isPassword && titleText == "Senha") {
        AlertText(
            textMessage = "Senha não atende as condições",
            modifier = Modifier.padding(top = 2.sdp, bottom = 6.sdp, start = 10.sdp)
        )
    } else if (isPassword && titleText == "Senha") {
        Text(
            "*Mínimo de 8 caracteres, com 1 símbolo especial, 1 letra maiúscula, 1 letra minúscula e um numeral",
            color = MaterialTheme.colorScheme.outline,
            modifier = Modifier.padding(5.sdp),
            fontSize = 10.ssp
        )

    } else if (textError != null) {
        textError.forEach {
            AlertText(
                textMessage = it,
                modifier = Modifier.padding(top = 6.sdp, bottom = 6.sdp, start = 10.sdp)
            )
        }
    }

}

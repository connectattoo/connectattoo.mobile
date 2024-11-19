package br.com.connectattoo.ui.screen_app.screens_apresentation.welcomepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.navigation.NavHostController
import br.com.connectattoo.ui.components.ImageLogo
import br.com.connectattoo.ui.theme.extendedColors
import connectattoo.composeapp.generated.resources.Layer_1
import connectattoo.composeapp.generated.resources.Res
import connectattoo.composeapp.generated.resources.Vector
import connectattoo.composeapp.generated.resources.image_splash
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource

@Composable
fun WelcomePage(navController: NavHostController) {
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
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
            ) {

                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(
                            top = 50.sdp,
                            start = 16.sdp,
                            end = 16.sdp,
                        ),
                    content = {
                        item {
                            ImageLogo()
                            Spacer(modifier = Modifier.padding(15.sdp))
                        }
                        item {
                            Text(
                                text = "VAMOS COMEÇAR, SEJA BEM-VINDO!",
                                fontSize = 25.ssp,
                                fontWeight = FontWeight(900),
                                textAlign = TextAlign.Center,
                                lineHeight = 25.ssp,
                                minLines = 2,
                                modifier = Modifier.fillMaxWidth()
                            )
                            Spacer(modifier = Modifier.padding(15.sdp))
                        }
                        item {
                            Text(
                                text = buildAnnotatedString {
                                    append("Nos ajude a direcionar sua jornada.\n")

                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append("Você é um tatuador")
                                    }

                                    append(" em busca de novos clientes ")

                                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                                        append("ou um cliente")
                                    }

                                    append(" em busca do tatuador ideal?")
                                },
                                fontSize = 14.ssp,
                                lineHeight = 20.ssp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.padding(25.sdp))
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceEvenly
                            ) {
                                Button(
                                    onClick = {
                                        navController.navigate("registerTattooArtist")
                                    },
                                    modifier = Modifier.weight(1f)
                                        .size(130.sdp).height(450.sdp),
                                    contentPadding = PaddingValues(vertical = 8.sdp),
                                    shape = RoundedCornerShape(8.sdp),

                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.extendedColors.purple500,
                                        contentColor = Color.White
                                    ),
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(
                                            painter = painterResource(Res.drawable.Vector),
                                            contentDescription = "Icone do tatuador",
                                            modifier = Modifier.size(84.sdp)
                                        )
                                        Spacer(modifier = Modifier.height(4.sdp))
                                        Text(
                                            text = "TATUADOR",
                                            fontSize = 13.ssp,
                                            color = Color.White,
                                            fontWeight = FontWeight(800)
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.padding(5.sdp))

                                Button(
                                    onClick = {
                                        navController.navigate("register")
                                    },
                                    modifier = Modifier.weight(1f)
                                        .size(130.sdp).height(450.sdp),
                                    contentPadding = PaddingValues(vertical = 8.sdp),
                                    shape = RoundedCornerShape(8.sdp),

                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = MaterialTheme.extendedColors.purple500,
                                        contentColor = Color.White
                                    ),
                                ) {
                                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                        Icon(
                                            painter = painterResource(Res.drawable.Layer_1),
                                            contentDescription = "Icone do tatuador",
                                            modifier = Modifier.size(84.sdp)
                                        )
                                        Spacer(modifier = Modifier.height(4.sdp))
                                        Text(
                                            text = "CLIENTE",
                                            fontSize = 13.ssp,
                                            color = Color.White,
                                            fontWeight = FontWeight(800)
                                        )
                                    }
                                }

                            }

                        }
                    }
                )
            }
        }
    }

}


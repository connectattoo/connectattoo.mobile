package br.com.connectattoo.ui.screen_app.screen_home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.connectattoo.domain.model.NearbyTattooArtists
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import connectattoo.composeapp.generated.resources.Res
import connectattoo.composeapp.generated.resources.location_icon
import connectattoo.composeapp.generated.resources.star
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource

@Composable
fun ListNearbyTattooArtist(
    listTattooNearbyTattooArtists: List<NearbyTattooArtists>,
    onAddMoreClicked: () -> Unit
) {
    val listState = rememberLazyListState()

    val initialPadding = remember { 16.dp }

    val paddingStart = if (listState.firstVisibleItemScrollOffset > 0) 0.sdp else initialPadding

    var visibleItemCount by remember { mutableStateOf(4) }

    LazyRow(
        state = listState,
        contentPadding = PaddingValues(start = paddingStart, end = 16.sdp),
        horizontalArrangement = Arrangement.spacedBy(8.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(listTattooNearbyTattooArtists.take(visibleItemCount).size) { actualIndex ->
            CardNearbyTattooArtist(listTattooNearbyTattooArtists[actualIndex])
        }

        item {
            AddMoreButton(onClick = {
                if (visibleItemCount < listTattooNearbyTattooArtists.size) {
                    visibleItemCount += 4
                    onAddMoreClicked()
                }
            })
        }
    }
}

@Composable
fun CardNearbyTattooArtist(tattooArtists: NearbyTattooArtists) {
    Card(
        modifier = Modifier
            .width(110.sdp)
            .height(150.sdp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(8.sdp),
    ) {

        Box {
            CoilImage(
                component = rememberImageComponent {
                    +ShimmerPlugin(
                        Shimmer.Flash(
                            baseColor = Color.DarkGray,
                            highlightColor = Color.LightGray
                        )
                    )
                    +CrossfadePlugin(
                        duration = 550
                    )

                },
                imageModel = { tattooArtists.tattoo },
                modifier = Modifier
                    .width(110.sdp)
                    .height(90.sdp)
                    .clip(RoundedCornerShape(topStart = 8.sdp, topEnd = 8.sdp)),

                imageOptions = ImageOptions(
                    alignment = Alignment.Center,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,

                    )
            )

            Surface(
                modifier = Modifier.height(25.sdp).align(Alignment.TopEnd)
                    .padding(top = 6.sdp, end = 6.sdp),
                shape = RoundedCornerShape(11.sdp),
                color = MaterialTheme.colorScheme.primary
            ) {
                Row(
                    modifier = Modifier.fillMaxHeight().padding(horizontal = 8.sdp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.sdp)
                ) {
                    Image(
                        modifier = Modifier.size(8.sdp),
                        painter = painterResource(Res.drawable.star),
                        contentDescription = "",
                    )
                    Text(
                        modifier = Modifier.offset(y = (-4).sdp),
                        fontSize = 8.ssp,
                        fontWeight = FontWeight.Bold,
                        text = tattooArtists.assessment ?: "5",
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.sdp)
                    .padding(top = 70.sdp)
                    .fillMaxHeight()
                    .align(Alignment.BottomCenter),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CoilImage(
                    component = rememberImageComponent {
                        +ShimmerPlugin(
                            Shimmer.Flash(
                                baseColor = Color.DarkGray,
                                highlightColor = Color.LightGray
                            )
                        )
                        +CrossfadePlugin(
                            duration = 550
                        )

                    },
                    imageModel = { tattooArtists.tattoo },
                    modifier = Modifier
                        .size(35.sdp)
                        .clip(CircleShape)
                        .border(2.sdp, Color.White, CircleShape),

                    imageOptions = ImageOptions(
                        alignment = Alignment.Center,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,

                        )
                )
                Text(
                    modifier = Modifier.offset(y = (-8).sdp),
                    fontSize = 8.ssp,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    text = tattooArtists.name ?: "",
                    color = Color.Black,
                    overflow = TextOverflow.Ellipsis
                )
                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally).offset(y = (-16).sdp).fillMaxHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.sdp),

                    ) {
                    Image(
                        modifier = Modifier.size(8.sdp).align(Alignment.Bottom),
                        painter = painterResource(Res.drawable.location_icon),
                        contentDescription = "",
                    )
                    Text(
                        modifier = Modifier.padding(top = 2.sdp),
                        fontSize = 9.ssp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = tattooArtists.address ?: "",
                        color = Color.Black
                    )
                }

            }
        }
    }
}



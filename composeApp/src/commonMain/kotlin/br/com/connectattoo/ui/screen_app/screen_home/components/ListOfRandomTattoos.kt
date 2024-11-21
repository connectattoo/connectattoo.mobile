package br.com.connectattoo.ui.screen_app.screen_home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import br.com.connectattoo.domain.model.RandomTattoo
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import connectattoo.composeapp.generated.resources.Res
import connectattoo.composeapp.generated.resources.like_icon
import connectattoo.composeapp.generated.resources.like_icon_true
import connectattoo.composeapp.generated.resources.save_icon
import connectattoo.composeapp.generated.resources.save_icon_true2
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource

@Composable
fun ListOfRandomTattoos(
    modifier: Modifier = Modifier, listTattooNearbyTattooArtists: List<RandomTattoo>,
    onAddMoreClicked: () -> Unit,
    onSavedClicked: (RandomTattoo, Boolean) -> Unit,
    onLikeClicked: (RandomTattoo, Boolean) -> Unit,
) {


    val listState = rememberLazyListState()

    val initialPadding = remember { 16.dp }

    val paddingStart = if (listState.firstVisibleItemScrollOffset > 0) 0.sdp else initialPadding

    var visibleItemCount by remember { mutableStateOf(4) }

    LazyRow(
        modifier = modifier,
        state = listState,
        contentPadding = PaddingValues(start = paddingStart, end = 16.sdp),
        horizontalArrangement = Arrangement.spacedBy(8.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(listTattooNearbyTattooArtists.take(visibleItemCount).size) { actualIndex ->
            CardRandomTattoos(
                listTattooNearbyTattooArtists[actualIndex],
                onSavedClicked = onSavedClicked,
                onLikeClicked = onLikeClicked
            )
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
fun CardRandomTattoos(
    randomTattoo: RandomTattoo,
    onSavedClicked: (RandomTattoo, Boolean) -> Unit,
    onLikeClicked: (RandomTattoo, Boolean) -> Unit,
) {
    var save by remember { mutableStateOf(randomTattoo.save) }
    var like by remember { mutableStateOf(randomTattoo.like) }
    Card(
        modifier = Modifier
            .padding(bottom = 24.sdp)
            .width(90.sdp)
            .height(105.sdp),
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
                imageModel = { randomTattoo.tattoo },
                modifier = Modifier
                    .width(110.sdp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(12.sdp)),

                imageOptions = ImageOptions(
                    alignment = Alignment.Center,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,

                    )
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            )

            Row(
                modifier = Modifier.padding(horizontal = 8.sdp).padding(top = 8.sdp)
                    .align(Alignment.TopEnd),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.sdp)
            ) {
                Image(
                    modifier = Modifier.size(10.sdp).clickable {
                        save = !save
                        onSavedClicked(randomTattoo, save)
                    },
                    painter = painterResource(if (save) Res.drawable.save_icon_true2 else Res.drawable.save_icon),
                    contentDescription = "save icon",
                )
                Image(
                    modifier = Modifier.size(10.sdp).clickable {
                        like = !like
                        onLikeClicked(randomTattoo, like)
                    },
                    painter = painterResource(if (like) Res.drawable.like_icon_true else Res.drawable.like_icon),
                    contentDescription = "like icon",
                )

            }

            Row(
                modifier = Modifier.padding(horizontal = 4.sdp).padding(top = 60.sdp)
                    .align(Alignment.BottomStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(3.sdp)
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
                    imageModel = { randomTattoo.tattoo },
                    modifier = Modifier
                        .size(22.sdp)
                        .clip(CircleShape)
                        .border(2.sdp, Color.White, CircleShape),

                    imageOptions = ImageOptions(
                        alignment = Alignment.Center,
                        contentDescription = null,
                        contentScale = ContentScale.Crop,

                        )
                )
                Column(
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.spacedBy(0.sdp)
                ) {
                    Text(
                        modifier = Modifier.offset(y = 3.sdp),
                        fontSize = 8.ssp,
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        text = randomTattoo.name ?: "",
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        modifier = Modifier.offset(y = (-12).dp),
                        fontSize = 6.ssp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        text = "Artista de Tatuagem",
                        color = Color.White
                    )
                }

            }
        }
    }
}
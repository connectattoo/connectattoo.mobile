package br.com.connectattoo.ui.screen_app.screen_home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.connectattoo.domain.model.TattoosBasedOnTagsHomeScreen
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.placeholder.PlaceholderPlugin
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

@Composable
fun HorizontalListWithCards(
    listTattoosBasedOnTagsHomeScreen: List<TattoosBasedOnTagsHomeScreen>,
    onAddMoreClicked: () -> Unit
) {
    val listState = rememberLazyListState()

    val initialPadding = remember { 16.dp }

    val paddingStart = if (listState.firstVisibleItemScrollOffset > 0) 0.dp else initialPadding

    var visibleItemCount by remember { mutableStateOf(4) }

    LazyRow(
        state = listState,
        contentPadding = PaddingValues(start = paddingStart, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.sdp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(listTattoosBasedOnTagsHomeScreen.take(visibleItemCount).size) { actualIndex ->
            CardWithImageAndTags(listTattoosBasedOnTagsHomeScreen[actualIndex])
        }

        item {
            AddMoreButton(onClick = {
                if (visibleItemCount < listTattoosBasedOnTagsHomeScreen.size) {
                    visibleItemCount += 4
                }
            })
        }
    }
}

@Composable
fun CardWithImageAndTags(tattoo: TattoosBasedOnTagsHomeScreen) {
    Card(
        modifier = Modifier
            .width(110.sdp)
            .height(140.sdp),
        shape = RoundedCornerShape(8.sdp),
    ) {

        Box {
            CoilImage(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp)),
                component = rememberImageComponent {
                    +ShimmerPlugin(
                        Shimmer.Flash(
                            baseColor = Color.White,
                            highlightColor = Color.LightGray,
                        )
                    )
                    +CrossfadePlugin(
                        duration = 550
                    )

                },
                imageModel = { tattoo.imageTattoo },
                imageOptions = ImageOptions(
                    alignment = Alignment.Center,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,

                    )
            )
            /*Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
            )*/
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.sdp)
                    .align(Alignment.BottomStart),
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 2.sdp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    tattoo.listTagHomeScreens?.take(2)?.forEach { tag ->
                        Text(
                            text = tag.title ?: "",
                            color = if (tag.backgroundDeepPurple) Color.White else MaterialTheme.colorScheme.primary,
                            fontSize = 8.ssp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(end = 8.sdp)
                                .clip(RoundedCornerShape(34.dp))
                                .background((if (tag.backgroundDeepPurple) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inverseOnSurface))
                                .padding(horizontal = 4.sdp)
                        )
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(2.sdp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    tattoo.listTagHomeScreens?.drop(2)?.take(2)?.forEach { tag ->
                        Text(
                            text = tag.title ?: "",
                            color = if (tag.backgroundDeepPurple) Color.White else MaterialTheme.colorScheme.primary,
                            fontSize = 8.ssp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .padding(end = 8.sdp)
                                .clip(RoundedCornerShape(34.dp))
                                .background(if (tag.backgroundDeepPurple) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inverseOnSurface)
                                .padding(horizontal = 4.sdp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AddMoreButton(onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .clickable { onClick() },
        shape = CircleShape,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add more",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}


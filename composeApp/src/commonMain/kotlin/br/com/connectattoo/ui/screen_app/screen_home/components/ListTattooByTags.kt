package br.com.connectattoo.ui.screen_app.screen_home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.connectattoo.domain.model.TattoosBasedOnTagsHomeScreen
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
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
                imageModel = { tattoo.imageTattoo },
                modifier = Modifier
                    .size(200.sdp)
                    .clip(RoundedCornerShape(12.dp)),

                imageOptions = ImageOptions(
                    alignment = Alignment.Center,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,

                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.sdp, bottom = 6.sdp)
                    .align(Alignment.BottomStart),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 2.sdp).height(18.sdp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    tattoo.listTagHomeScreens?.take(2)?.forEach { tag ->

                        Box(
                            modifier = Modifier
                                .padding(end = 2.sdp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(if (tag.backgroundDeepPurple) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inverseOnSurface)
                                .height(18.sdp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = tag.title ?: "",
                                color = if (tag.backgroundDeepPurple) Color.White else MaterialTheme.colorScheme.primary,
                                fontSize = 8.ssp,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(horizontal = 8.sdp).offset(y = (-3).sdp)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier.fillMaxWidth().padding(2.sdp).height(18.sdp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    tattoo.listTagHomeScreens?.drop(2)?.take(2)?.forEach { tag ->
                        Box(
                            modifier = Modifier
                                .padding(end = 2.sdp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(if (tag.backgroundDeepPurple) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inverseOnSurface)
                                .height(18.sdp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = tag.title ?: "",
                                color = if (tag.backgroundDeepPurple) Color.White else MaterialTheme.colorScheme.primary,
                                fontSize = 8.ssp,
                                textAlign = TextAlign.Center,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.padding(horizontal = 8.sdp).offset(y = (-3).sdp)
                            )
                        }
                    }
                }
            }
        }
    }
}

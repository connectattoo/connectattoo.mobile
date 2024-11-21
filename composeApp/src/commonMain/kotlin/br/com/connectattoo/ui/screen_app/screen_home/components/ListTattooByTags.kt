package br.com.connectattoo.ui.screen_app.screen_home.components

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.connectattoo.domain.model.TattoosBasedOnTagsHomeScreen
import connectattoo.composeapp.generated.resources.Res
import connectattoo.composeapp.generated.resources.image_splash
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp
import org.jetbrains.compose.resources.painterResource

@Composable
fun HorizontalListWithCards(
    listTattoosBasedOnTagsHomeScreen: List<TattoosBasedOnTagsHomeScreen>,
    onAddMoreClicked: () -> Unit
) {
    val listState = rememberLazyListState()

    val initialPadding = remember { 16.dp }

    val paddingStart = if (listState.firstVisibleItemScrollOffset > 0) 0.dp else initialPadding

    LazyRow(
        state = listState,
        contentPadding = PaddingValues(start = paddingStart, end = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.sdp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        items(listTattoosBasedOnTagsHomeScreen.take(4).size) { tattoo ->
            CardWithImageAndTags(
                listTattoosBasedOnTagsHomeScreen
            )
        }

        item {
            AddMoreButton(onClick = onAddMoreClicked)
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
}@Composable
fun CardWithImageAndTags(listTattoosBasedOnTagsHomeScreen: List<TattoosBasedOnTagsHomeScreen>) {
    Card(
        modifier = Modifier
            .width(110.sdp)
            .height(140.sdp),
        shape = RoundedCornerShape(12.sdp),
    ) {
        val image: Painter = painterResource(Res.drawable.image_splash)
        Box {
            Image(
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
            )
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
                    listTattoosBasedOnTagsHomeScreen.forEach { tattoosBasedOnTags ->
                        tattoosBasedOnTags.listTagHomeScreens?.take(2)?.forEach { tag ->
                            Text(
                                text = tag.title ?: "",
                                color = Color.White,
                                fontSize = 8.ssp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(end = 8.sdp)
                                    .clip(RoundedCornerShape(34.dp))
                                    .background(MaterialTheme.colorScheme.primary)
                                    .padding(horizontal = 4.sdp)
                            )
                        }
                    }
                }

                // Segunda linha - Pegando as próximas 2 tags de cada item de TattoosBasedOnTagsHomeScreen
                Row(
                    modifier = Modifier.fillMaxWidth().padding(2.sdp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    listTattoosBasedOnTagsHomeScreen.forEach { tattoosBasedOnTags ->
                        // Verifica se a lista de tags não é nula e tem pelo menos 2 tags a mais
                        tattoosBasedOnTags.listTagHomeScreens?.drop(2)?.take(2)?.forEach { tag ->
                            Text(
                                text = tag.title ?: "",
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 8.ssp,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier
                                    .padding(end = 8.sdp)
                                    .clip(RoundedCornerShape(34.dp))
                                    .background(MaterialTheme.colorScheme.inverseOnSurface)
                                    .padding(horizontal = 4.sdp)
                            )
                        }
                    }
                }
            }
        }
    }
}


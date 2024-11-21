package br.com.connectattoo.ui.screen_app.screen_home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.connectattoo.ui.components.NavigationBar
import br.com.connectattoo.ui.components.ScaffoldCustom
import br.com.connectattoo.ui.screen_app.listNearbyTattooArtists
import br.com.connectattoo.ui.screen_app.listRandomTattoo
import br.com.connectattoo.ui.screen_app.listTattoosBasedOnTagsHomeScreen
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp


@Composable
fun Screen(navController: NavController) {


    Column {
        ScaffoldCustom(
            modifier = Modifier,
            showActions = true,
            shadowBelowTopBar = 0.dp,
            showButtonToReturn = false,
            isLoading = false,
            navigationUp = navController,
            showTopBar = true,
            showBottomBarNavigation = true,
            bottomNavigationBar = { NavigationBar(navController) },
            contentToUse = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    contentPadding = it
                ) {
                    item {
                        Header(
                            name = "Pedro",
                            modifier = Modifier.padding(start = 12.sdp, end = 4.sdp)
                        )

                    }
                    item {
                        HorizontalListWithCards(
                            listTattoosBasedOnTagsHomeScreen,
                            onAddMoreClicked = {

                            })
                    }
                    item {
                        Spacer(modifier = Modifier.padding(top = 16.sdp))
                        Text(
                            modifier = Modifier.padding(start = 16.sdp),
                            text = "Estes são os tatuadores mais próximos de você",
                            fontSize = 11.ssp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                        )
                        Spacer(modifier = Modifier.padding(top = 8.sdp))
                    }
                    item {
                        ListNearbyTattooArtist(listNearbyTattooArtists, onAddMoreClicked = {

                        })
                    }
                    item {
                        Spacer(modifier = Modifier.padding(top = 16.sdp))
                        Text(
                            modifier = Modifier.padding(start = 16.sdp),
                            text = "Encontre inspiração em cada detalhe",
                            fontSize = 11.ssp,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleSmall,
                            maxLines = 1,
                            textAlign = TextAlign.Start,
                        )
                        Spacer(modifier = Modifier.padding(top = 8.sdp))
                    }
                    item {
                        ListOfRandomTattoos(modifier = Modifier,
                            listRandomTattoo,
                            onAddMoreClicked = {

                            },
                            onLikeClicked = { randomTattoo, like ->

                            },
                            onSavedClicked = { randomTattoo, save ->

                            })
                    }
                }

            })
    }
}
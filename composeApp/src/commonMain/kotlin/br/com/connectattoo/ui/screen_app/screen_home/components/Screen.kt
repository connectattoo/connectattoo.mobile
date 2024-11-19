package br.com.connectattoo.ui.screen_app.screen_home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.connectattoo.domain.model.TagHomeScreen
import br.com.connectattoo.domain.model.TattoosBasedOnTagsHomeScreen
import br.com.connectattoo.ui.components.NavigationBar
import br.com.connectattoo.ui.components.ScaffoldCustom
import br.com.connectattoo.ui.screen_app.listTattoosBasedOnTagsHomeScreen
import network.chaintech.sdpcomposemultiplatform.sdp


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
                        Header(name = "Pedro", modifier = Modifier.padding( start = 12.sdp, end = 4.sdp))

                    }
                    item {
                        HorizontalListWithCards(listTattoosBasedOnTagsHomeScreen, onAddMoreClicked = {

                        })
                    }
                }

            })
    }
}
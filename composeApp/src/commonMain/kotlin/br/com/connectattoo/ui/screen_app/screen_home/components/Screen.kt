package br.com.connectattoo.ui.screen_app.screen_home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.connectattoo.ui.components.NavigationBar
import br.com.connectattoo.ui.components.ScaffoldCustom


@Composable
fun Screen(navController: NavController) {


    Column(modifier = Modifier.navigationBarsPadding()) {
        ScaffoldCustom(
            modifier = Modifier,
            //titleTopBar = stringResource(R.string.hello, name.value.capitalizeFirstLetter()),
            //isLoading = taskState is TaskState.Loading,
            //titleTopBarColor = MaterialTheme.colorScheme.scrim,
            //titleTopBarAligh = Alignment.CenterStart,
            showActions = true,
            shadowBelowTopBar = 0.dp,
            showButtonToReturn = false,
            navigationUp = navController,
            showTopBar = true,
            showBottomBarNavigation = true,
            bottomNavigationBar = { NavigationBar(navController) },
            contentToUse = {

            })
    }
}
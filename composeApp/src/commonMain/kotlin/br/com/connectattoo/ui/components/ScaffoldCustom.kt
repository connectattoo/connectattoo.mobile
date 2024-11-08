package br.com.connectattoo.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


//This scaffold is customized to Design 2.0?
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldCustom(
   isLoading: Boolean = false,
   shadowBelowTopBar: Dp = 4.dp,
   showTopBar: Boolean = false,
   actions: @Composable RowScope.() -> Unit = {},
   showActions: Boolean = false,
   showButtonToReturn: Boolean = false,
   showBottomBarNavigation: Boolean = false,
   navigationUp: NavController,
   bottomNavigationBar: @Composable () -> Unit = {},
   contentToUse: @Composable (PaddingValues) -> Unit = {},
   modifier: Modifier = Modifier){
    Scaffold(
        /*
        topBar = {
            if (showTopBar) {
                Surface(shadowElevation = shadowBelowTopBar) {
                    TopAppBar(
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = MaterialTheme.colorScheme.onPrimary,
                            titleContentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                        title = {},
                        navigationIcon = {
                            Box(modifier = Modifier
                                .fillMaxWidth(),
                                contentAlignment = Center){
                                ImageLogo(
                                    modifier = Modifier.size(
                                        width = 100.dp,
                                        height = 100.dp)
                                        .padding(8.dp),
                                    isBlack = false)
                            }
                        },
                        actions = {
                            if(showActions) actions()
                            else Spacer(modifier = Modifier.size(ButtonDefaults.IconSize))
                        }
                    )

                }
            }
        },

         */
        bottomBar = {
            if (showBottomBarNavigation) {
                bottomNavigationBar()
            }
        },
        content = {
            it -> contentToUse(it)
        },
        modifier = modifier.shadow(4.dp)
    )


}
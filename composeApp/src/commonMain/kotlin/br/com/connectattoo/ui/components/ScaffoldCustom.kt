package br.com.connectattoo.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
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
        bottomBar = {
            if (showBottomBarNavigation) {
                bottomNavigationBar()
            }
        },
        content = {
            if (isLoading)
                IndeterminateCircularIndicator()
            else {
                contentToUse(it)
            }
        },
        modifier = modifier.shadow(4.dp)
    )


}
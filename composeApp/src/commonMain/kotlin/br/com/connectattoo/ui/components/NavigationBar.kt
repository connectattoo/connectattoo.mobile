package br.com.connectattoo.ui.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import network.chaintech.sdpcomposemultiplatform.sdp
import org.jetbrains.compose.resources.painterResource

@Composable
fun NavigationBar(navController: NavController) {
    val items = listOf(
        NavigationBarItems.Home,
        NavigationBarItems.Chat,
        NavigationBarItems.Explorar,
        NavigationBarItems.Profile,
    )
    BottomAppBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = Color.Black,

    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->

            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(item.icons),
                        modifier = Modifier
                            .size(30.sdp),
                        tint = Color.White,
                        contentDescription = null
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = if (currentRoute?.startsWith(item.group) == true) MaterialTheme.colorScheme.onBackground else Color.Transparent
                ),
                selected = currentRoute?.startsWith(item.group) == true,
                onClick = {
                    val isSameGroup =
                        items.any { it.group == item.group && currentRoute?.startsWith(it.route) == true }

                    if (!isSameGroup) {
                        navController.navigate(item.route) {
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
package br.com.connectattoo.ui.components

import connectattoo.composeapp.generated.resources.Chat_icon
import connectattoo.composeapp.generated.resources.Explore_icon
import connectattoo.composeapp.generated.resources.Res
import connectattoo.composeapp.generated.resources.home_icon
import connectattoo.composeapp.generated.resources.profile_icon
import org.jetbrains.compose.resources.DrawableResource

sealed class NavigationBarItems(
    var title: String,
    var route: String,
    var icons: DrawableResource,
    var group: String
) {
    data object Home : NavigationBarItems(
        title = "home",
        route = "home",
        icons = Res.drawable.home_icon,
        group = "home"
    )

    data object Chat : NavigationBarItems(
        title = "chat",
        route = "login",
        icons = Res.drawable.Chat_icon,
        group = "chat"
    )

    data object Explorar : NavigationBarItems(
        title = "Explorar",
        route = "login",
        icons = Res.drawable.Explore_icon,
        group = "Explorar"
    )
    data object Profile : NavigationBarItems(
        title = "Perfil",
        route = "login",
        icons = Res.drawable.profile_icon,
        group = "Perfil"
    )
}

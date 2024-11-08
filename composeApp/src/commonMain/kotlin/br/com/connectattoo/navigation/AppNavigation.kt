package br.com.connectattoo.navigation

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import br.com.connectattoo.theme.ConnectattooTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ConnectattooTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets(0))

        ) {
            AccountManager()
        }
    }
}

@Composable
fun AccountManager() {
    ConnectattooTheme(
        content = {
            NavHostAccountManager()
        }
    )
}
@Composable
fun PresentationManager() {
    ConnectattooTheme(
        content = {
            Presentation()
        }
    )
}
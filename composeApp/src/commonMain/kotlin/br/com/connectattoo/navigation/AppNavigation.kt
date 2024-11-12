package br.com.connectattoo.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import br.com.connectattoo.ui.theme.ConnectattooTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ConnectattooTheme {
        Scaffold {
            PresentationManager()
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
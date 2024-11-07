package br.com.connectattoo.navigation

import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import br.com.connectattoo.theme.ConnectattooTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ConnectattooTheme {
        Surface {
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
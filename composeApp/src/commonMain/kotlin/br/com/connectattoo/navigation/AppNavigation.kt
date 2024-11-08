package br.com.connectattoo.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import br.com.connectattoo.theme.ConnectattooTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    ConnectattooTheme {
        Surface {
            Scaffold {
                PresentationManager()
            }
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
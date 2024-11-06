package br.com.connectattoo.navigation

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Surface {
            AccountManager()
        }
    }
}

@Composable
fun AccountManager() {
    MaterialTheme(
        content = {
            NavHostAccountManager()
        }
    )
}
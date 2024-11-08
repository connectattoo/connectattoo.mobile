package br.com.connectattoo

import androidx.compose.ui.window.ComposeUIViewController
import br.com.connectattoo.di.initKoin
import br.com.connectattoo.navigation.App

fun MainViewController() = ComposeUIViewController(configure = { initKoin()}) { App() }
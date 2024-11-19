package br.com.connectattoo.ui.screen_app.screens_apresentation.splashScreen

sealed class SplashFormEvent {
    data object Refresh : SplashFormEvent()
}
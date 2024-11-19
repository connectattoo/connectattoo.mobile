package br.com.connectattoo.ui.screen_app.account_manager.accountConfirmation

sealed class ConfirmAccountFormEvent {
    //data class TokenDataChanged(val name: String) : ConfirmAccountFormEvent()
    data object Refresh : ConfirmAccountFormEvent()
}
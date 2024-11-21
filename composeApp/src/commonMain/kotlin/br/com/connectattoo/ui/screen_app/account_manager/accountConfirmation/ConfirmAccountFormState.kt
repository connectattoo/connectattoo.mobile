package br.com.connectattoo.ui.screen_app.account_manager.accountConfirmation

import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.util.ValidationMessagePassword


data class ConfirmAccountFormState(
    val clientTokenData: String = "",
    val clientTokenDataError: List<String>? = null,
)

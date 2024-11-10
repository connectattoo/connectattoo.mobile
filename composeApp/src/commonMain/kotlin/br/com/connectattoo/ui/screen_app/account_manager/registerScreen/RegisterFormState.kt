package br.com.connectattoo.ui.screen_app.account_manager.registerScreen

import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.util.ValidationMessagePassword


data class RegisterFormState(
    val name: String = "",
    val nameError: List<String>? = null,
    val email: String = "",
    val emailError: List<String>? = null,
    val password: String = "",
    val passwordError: List<String>? = null,
    val passwordErrorMessages: List<ValidationMessagePassword>? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: List<String>? = null,
    val birthDate: String = "",
    val birthDateError: List<String>? = null,
    val privacyPolicy: Boolean = false,
     val clientTokenData: TokenData = TokenData("")
)

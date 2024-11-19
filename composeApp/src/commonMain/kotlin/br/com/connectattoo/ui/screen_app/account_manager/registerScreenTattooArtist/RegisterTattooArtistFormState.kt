package br.com.connectattoo.ui.screen_app.account_manager.registerScreenTattooArtist

import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.util.ValidationMessagePassword


data class RegisterTattooArtistFormState(
    val name: String = "",
    val nameError: List<String>? = null,
    val email: String = "",
    val emailError: List<String>? = null,
    val password: String = "",
    val passwordError: List<String>? = null,
    val passwordErrorMessages: List<ValidationMessagePassword>? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: List<String>? = null,
    val zipCode: String = "",
    val zipCodeError: List<String>? = null,
    val street: String = "",
    val streetError: List<String>? = null,
    val number: String = "",
    val numberError: List<String>? = null,
    val city: String = "",
    val cityError: List<String>? = null,
    val stateAddress: String = "",
    val stateAddressError: List<String>? = null,
    val birthDate: String = "",
    val birthDateError: List<String>? = null,
    val privacyPolicy: Boolean = false,
     val clientTokenData: String = ""
)

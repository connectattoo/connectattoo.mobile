package br.com.connectattoo.ui.screen_app.account_manager.registerScreenTattooArtist

sealed class RegisterTattooArtistFormEvent {
    data class NameChanged(val name: String) : RegisterTattooArtistFormEvent()
    data class EmailChanged(val email: String) : RegisterTattooArtistFormEvent()
    data class PasswordChanged(val password: String) : RegisterTattooArtistFormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterTattooArtistFormEvent()
    data class ZipCodeChanged(val zipCode: String) : RegisterTattooArtistFormEvent()
    data class StreetChanged(val street: String) : RegisterTattooArtistFormEvent()
    data class NumberChanged(val number: String) : RegisterTattooArtistFormEvent()
    data class CityChanged(val city: String) : RegisterTattooArtistFormEvent()
    data class StateChanged(val state: String) : RegisterTattooArtistFormEvent()
    data class BirthDateChanged(val birthDate: String) : RegisterTattooArtistFormEvent()
    data class PrivacyPolicyChanged(val privacyPolicy: Boolean) : RegisterTattooArtistFormEvent()
    data object Submit : RegisterTattooArtistFormEvent()
}
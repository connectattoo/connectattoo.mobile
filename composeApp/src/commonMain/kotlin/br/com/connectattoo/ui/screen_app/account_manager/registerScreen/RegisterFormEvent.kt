package br.com.connectattoo.ui.screen_app.account_manager.registerScreen

sealed class RegisterFormEvent {
    data class NameChanged(val name: String) : RegisterFormEvent()
    data class EmailChanged(val email: String) : RegisterFormEvent()
    data class PasswordChanged(val password: String) : RegisterFormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegisterFormEvent()
    data class BirthDateChanged(val birthDate: String) : RegisterFormEvent()
    data class PrivacyPolicyChanged(val privacyPolicy: Boolean) : RegisterFormEvent()
    data object Submit : RegisterFormEvent()
}
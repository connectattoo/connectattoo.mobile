package br.com.connectattoo.ui.screen_app.account_manager.registerScreenTattooArtist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import br.com.connectattoo.domain.model.ClientData
import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.repository.ValidationRepository
import br.com.connectattoo.domain.use_cases.RegisterClientUseCase
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.util.ValidationEvent
import com.soujunior.domain.use_case.util.ValidationResult
import com.soujunior.domain.use_case.util.ValidationResultPassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterTattooArtistViewModelImpl(
    private val registerClientUseCase: RegisterClientUseCase,
    private val validation: ValidationRepository
) : RegisterTattooArtistViewModel() {

    override var state by mutableStateOf(RegisterTattooArtistFormState())
    override val validationEventChannel = Channel<ValidationEvent>()
    override val validationEvents = validationEventChannel.receiveAsFlow()
    override fun success(resultPostRegister: TokenData) {
        state = state.copy(clientTokenData = resultPostRegister)
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    override fun failed(exception: Throwable?) {
        setMessage.value = exception?.message ?: "Unknown Error"
        viewModelScope.launch { validationEventChannel.send(ValidationEvent.Failed) }
    }

    override val message: StateFlow<String> get() = setMessage
    private val setMessage = MutableStateFlow("")

    private val _taskState: MutableStateFlow<TaskState> = MutableStateFlow(TaskState.Idle)
    override val taskState: StateFlow<TaskState> = _taskState


    private fun hasError(result: ValidationResult): Boolean {
        return listOf(result).any { !it.success }
    }

    private fun hasErrorPassword(result: ValidationResultPassword): Boolean {
        return listOf(result).any { !it.success }
    }

    override fun enableButton(): Boolean {
        val nameResult = validation.validateName(state.name)
        val emailResult = validation.validateEmail(state.email)
        val passwordResult = validation.validatePassword(password = state.password)
        val repeatedPasswordResult =
            validation.validateRepeatedPassword(state.password, state.repeatedPassword)

        return state.name.isNotBlank() &&
                state.email.isNotBlank() &&
                state.password.isNotBlank() &&
                state.repeatedPassword.isNotBlank() &&
                nameResult.errorMessage == null &&
                emailResult.errorMessage == null &&
                passwordResult.errorMessage == null &&
                repeatedPasswordResult.errorMessage == null &&
                state.privacyPolicy
    }

    override fun change(
        name: String?,
        email: String?,
        password: String?,
        repeatedPassword: String?,
        zipCode: String?,
        street: String?,
        number: String?,
        city: String?,
        stateAddress: String?,
        birthDate: String?,
        privacy: Boolean?
    ) {
        when {
            name != null -> {
                state = state.copy(name = name)
                val nameResult = validation.validateName(state.name)
                state = if (hasError(nameResult)) state.copy(nameError = nameResult.errorMessage)
                else state.copy(nameError = null)
            }


            email != null -> {
                state = state.copy(email = email)
                val emailResult = validation.validateEmail(state.email)
                state = if (hasError(emailResult)) state.copy(emailError = emailResult.errorMessage)
                else state.copy(emailError = null)
            }

            password != null -> {
                state = state.copy(password = password)
                val passwordResult = validation.validatePassword(
                    password = state.password
                )
                state =
                    if (hasErrorPassword(passwordResult)) state.copy(
                        passwordErrorMessages = passwordResult.errorMessage,
                        passwordError = listOf("Senha não atende as condições")
                    )
                    else state.copy(passwordErrorMessages = null, passwordError = null)
                change(repeatedPassword = state.repeatedPassword)
            }

            repeatedPassword != null -> {
                state = state.copy(repeatedPassword = repeatedPassword)
                val repeatedPasswordResult = validation.validateRepeatedPassword(
                    repeatedPassword = state.repeatedPassword,
                    password = state.password
                )
                state =
                    if (hasError(repeatedPasswordResult)) state.copy(repeatedPasswordError = repeatedPasswordResult.errorMessage)
                    else state.copy(repeatedPasswordError = null)
            }
            zipCode != null -> {
                state = state.copy(zipCode = zipCode)
                val zipCodeResult = validation.validateName(state.zipCode)
                state = if (hasError(zipCodeResult)) state.copy(zipCodeError = zipCodeResult.errorMessage)
                else state.copy(zipCodeError = null)
            }
            street != null -> {
                state = state.copy(street = street)
                val streetResult = validation.validateName(state.street)
                state = if (hasError(streetResult)) state.copy(streetError = streetResult.errorMessage)
                else state.copy(streetError = null)
            }
            number != null -> {
                state = state.copy(number = number)
                val numberResult = validation.validateName(state.number)
                state = if (hasError(numberResult)) state.copy(numberError = numberResult.errorMessage)
                else state.copy(numberError = null)
            }
            city != null -> {
                state = state.copy(city = city)
                val cityResult = validation.validateName(state.city)
                state = if (hasError(cityResult)) state.copy(cityError = cityResult.errorMessage)
                else state.copy(cityError = null)
            }
            stateAddress != null -> {
                state = state.copy(stateAddress = stateAddress)
                val stateResult = validation.validateName(state.stateAddress)
                state = if (hasError(stateResult)) state.copy(stateAddressError = stateResult.errorMessage)
                else state.copy(stateAddressError = null)
            }

            birthDate != null -> {
                state = state.copy(birthDate = birthDate)
                val birthDateResult = validation.validateDate(state.birthDate)
                state =
                    if (hasError(birthDateResult)) state.copy(birthDateError = birthDateResult.errorMessage)
                    else state.copy(birthDateError = null)
            }

            privacy != null -> {
                state = state.copy(privacyPolicy = privacy)
                val privacyPolicyResult =
                    validation.validatePrivacyPolicy(value = state.privacyPolicy)
                state =
                    if (hasError(privacyPolicyResult)) state.copy(privacyPolicy = privacyPolicyResult.success)
                    else state.copy(repeatedPasswordError = null)
            }
        }
    }

    override fun onEvent(event: RegisterTattooArtistFormEvent) {
        when (event) {
            is RegisterTattooArtistFormEvent.NameChanged -> change(name = event.name)
            is RegisterTattooArtistFormEvent.EmailChanged -> change(email = event.email)
            is RegisterTattooArtistFormEvent.PasswordChanged -> change(password = event.password)
            is RegisterTattooArtistFormEvent.ConfirmPasswordChanged -> change(repeatedPassword = event.confirmPassword)
            is RegisterTattooArtistFormEvent.ZipCodeChanged -> change(zipCode = event.zipCode)
            is RegisterTattooArtistFormEvent.StreetChanged -> change(street = event.street)
            is RegisterTattooArtistFormEvent.NumberChanged -> change(number = event.number)
            is RegisterTattooArtistFormEvent.CityChanged -> change(city = event.city)
            is RegisterTattooArtistFormEvent.StateChanged -> change(stateAddress = event.state)
            is RegisterTattooArtistFormEvent.PrivacyPolicyChanged -> change(privacy = event.privacyPolicy)
            is RegisterTattooArtistFormEvent.Submit -> submitData()
            is RegisterTattooArtistFormEvent.BirthDateChanged -> change(birthDate = event.birthDate)
        }
    }

    override fun submitData() {
        val emailResult = validation.validateEmail(state.email)
        val name = validation.validateName(state.name)
        val confirmPasswordResult =
            validation.validateRepeatedPassword(state.repeatedPassword, state.password)
        val birthDateResult = validation.validateDate(state.birthDate)
        val passwordResult = validation.validatePassword(state.password)
        val hasError =
            listOf(emailResult, name, confirmPasswordResult, birthDateResult).any { !it.success }
        val hasErrorPassword = listOf(passwordResult).any { !it.success }
        if (hasErrorPassword) {
            state = state.copy(
                passwordError = listOf("Senha não atende as condições")
            )

        }
        if (hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                nameError = name.errorMessage,
                repeatedPasswordError = confirmPasswordResult.errorMessage,
                birthDateError = birthDateResult.errorMessage
            )
            return
        }
        _taskState.value = TaskState.Loading
        viewModelScope.launch {
            val result = registerClientUseCase.execute(
                ClientData(
                    name = state.name.replace(" ", ""),
                    email = state.email.replace(" ", ""),
                    password = state.password.replace(" ", ""),
                    birthDate = state.birthDate.replace(" ", ""),
                    termsAccepted = state.privacyPolicy
                )
            )
            result.handleResult(::success, ::failed)

            _taskState.value = TaskState.Idle
        }
    }
}
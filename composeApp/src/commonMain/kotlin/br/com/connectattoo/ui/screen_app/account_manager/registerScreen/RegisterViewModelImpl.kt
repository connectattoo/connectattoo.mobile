package br.com.connectattoo.ui.screen_app.account_manager.registerScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import br.com.connectattoo.domain.model.ClientData
import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.domain.repository.ValidationRepository
import br.com.connectattoo.domain.use_cases.auth.RegisterClientUseCase
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.util.ValidationEvent
import com.soujunior.domain.use_case.util.ValidationResult
import com.soujunior.domain.use_case.util.ValidationResultPassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModelImpl(
    private val registerClientUseCase: RegisterClientUseCase,
    private val validation: ValidationRepository
) : RegisterViewModel() {

    override var state by mutableStateOf(RegisterFormState())
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
        birthDate: String?,
        repeatedPassword: String?,
        privacy: Boolean?,
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

    override fun onEvent(event: RegisterFormEvent) {
        when (event) {
            is RegisterFormEvent.NameChanged -> change(name = event.name)
            is RegisterFormEvent.EmailChanged -> change(email = event.email)
            is RegisterFormEvent.PasswordChanged -> change(password = event.password)
            is RegisterFormEvent.ConfirmPasswordChanged -> change(repeatedPassword = event.confirmPassword)
            is RegisterFormEvent.PrivacyPolicyChanged -> change(privacy = event.privacyPolicy)
            is RegisterFormEvent.Submit -> submitData()
            is RegisterFormEvent.BirthDateChanged -> change(birthDate = event.birthDate)
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
package br.com.connectattoo.ui.screen_app.account_manager.registerScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import br.com.connectattoo.domain.repository.ValidationRepository
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.util.ValidationEvent
import com.soujunior.domain.use_case.util.ValidationResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModelImpl(
    // private val signUpUseCase: SignUpUseCase,
    private val validation: ValidationRepository
) : RegisterViewModel() {

    override var state by mutableStateOf(RegisterFormState())
    override val validationEventChannel = Channel<ValidationEvent>()
    override val validationEvents = validationEventChannel.receiveAsFlow()

    override val message: StateFlow<String> get() = setMessage
    private val setMessage = MutableStateFlow("")

    private val _taskState: MutableStateFlow<TaskState> = MutableStateFlow(TaskState.Idle)
    override val taskState: StateFlow<TaskState> = _taskState


    private fun hasError(result: ValidationResult): Boolean {
        return listOf(result).any { !it.success }
    }

    override fun enableButton(): Boolean {
        val nameResult = validation.validateName(state.name)
        val emailResult = validation.validateEmail(state.email)
        val passwordResult = validation.validatePassword(password = state.password)
        val repeatedPasswordResult =
            validation.validateRepeatedPassword(state.password, state.repeatedPassword)

        return state.name.isNotBlank() &&
                state.lastName.isNotBlank() &&
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
        lastName: String?,
        email: String?,
        phone: String?,
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
                    if (hasError(passwordResult)) state.copy(passwordError = passwordResult.errorMessage)
                    else state.copy(passwordError = null)
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
        _taskState.value = TaskState.Loading
        viewModelScope.launch {
            /* val result = signUpUseCase.execute(
                 SignUpModel(
                     firstName = state.name.replace(" ", ""),
                     lastName = state.lastName.replace(" ", ""),
                     email = state.email.replace(" ", ""),
                     phone = state.phone.replace(" ", ""),
                     password = state.password.replace(" ", ""),
                     passwordConfirmation = state.password.replace(" ", ""),
                     isPrivacyPolicyAccepted = state.privacyPolicy
                 )
             )
             result.handleResult(::success, ::failed)
             _taskState.value = TaskState.Idle*/
        }
    }
}
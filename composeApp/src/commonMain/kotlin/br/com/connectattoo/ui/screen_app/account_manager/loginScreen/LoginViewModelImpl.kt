package br.com.connectattoo.ui.screen_app.account_manager.loginScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import br.com.connectattoo.domain.repository.ValidationRepository
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.util.ValidationEvent
import com.soujunior.domain.use_case.util.ValidationResult
import com.soujunior.domain.use_case.util.ValidationResultPassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModelImpl(
    //  private val loginUseCase: LoginUseCase,
    private val validation: ValidationRepository,
) : LoginViewModel() {

    override var state by mutableStateOf(LoginFormState())
    override val validationEventChannel = Channel<ValidationEvent>()
    override val validationEvents = validationEventChannel.receiveAsFlow()

    override val message: StateFlow<String> get() = setMessage
    private val setMessage = MutableStateFlow("")

    private val _taskState: MutableStateFlow<TaskState> = MutableStateFlow(TaskState.Idle)
    override val taskState: StateFlow<TaskState> = _taskState

    override fun failed(exception: Throwable?) {
        setMessage.value = exception?.message.toString()
        viewModelScope.launch { validationEventChannel.send(ValidationEvent.Failed) }
    }

    override fun success(resultPostLogin: String) {
        setMessage.value = resultPostLogin
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    private fun hasError(result: ValidationResult): Boolean {
        return listOf(result).any { !it.success }
    }

    override fun enableButton(): Boolean {
        return state.email.isNotBlank() &&
                state.password.isNotBlank()
    }

    private fun change(
        email: String? = null,
        password: String? = null
    ) {
        when {
            email != null -> {
                state = state.copy(email = email)
                val emailResult = validation.validateEmail(state.email)
                state = if (hasError(emailResult)) state.copy(emailError = emailResult.errorMessage)
                else state.copy(emailError = null)
            }

            password != null -> {
                state = state.copy(password = password)
                val passwordResult = state.password.isNotEmpty()

                state =
                    if (!passwordResult) state.copy(passwordError = listOf("O campo não pode ficar em branco!"))
                    else state.copy(passwordError = null)

            }

        }
    }

    override fun onEvent(event: LoginFormEvent) {
        when (event) {
            is LoginFormEvent.EmailChanged -> change(email = event.email)
            is LoginFormEvent.PasswordChanged -> change(password = event.password)
            is LoginFormEvent.Submit -> submitData()
        }
    }

    override fun submitData() {
        val emailResult = validation.validateEmail(state.email)
        val passwordResult = state.password.isNotEmpty()

        state =
            if (!passwordResult) state.copy(passwordError = listOf("O campo não pode ficar em branco!"))
            else state.copy(passwordError = null)

        val hasError = listOf(emailResult).any { !it.success }

        if (hasError) {
            state = state.copy(emailError = emailResult.errorMessage)
            return
        }

        _taskState.value = TaskState.Loading
        viewModelScope.launch {
            /*val result = loginUseCase.execute(
                LoginModel(
                    email = state.email,
                    password = state.password
                )
            )*/

            // result.handleResult(::success, ::failed)
            _taskState.value = TaskState.Idle
        }
    }
}
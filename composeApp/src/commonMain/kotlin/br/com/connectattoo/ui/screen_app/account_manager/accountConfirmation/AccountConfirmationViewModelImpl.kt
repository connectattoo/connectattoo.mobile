package br.com.connectattoo.ui.screen_app.account_manager.accountConfirmation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import br.com.connectattoo.domain.use_cases.auth.ConfirmEmailUseCase
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.util.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AccountConfirmationViewModelImpl(
    private val confirmEmailUseCase: ConfirmEmailUseCase
) : AccountConfirmationViewModel() {

    override var state by mutableStateOf(ConfirmAccountFormState())
    override val validationEventChannel = Channel<ValidationEvent>()
    private val setMessage = MutableStateFlow("")
    override val message: StateFlow<String>
        get() = setMessage
    private val _taskState: MutableStateFlow<TaskState> = MutableStateFlow(TaskState.Idle)
    override val taskState: StateFlow<TaskState> get() = _taskState
    init {
        checkUser()
    }
    override fun success(clientTokenData: String) {
        state = state.copy(clientTokenData = clientTokenData)
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    override fun failed(exception: Throwable?) {
        setMessage.value = exception?.message ?: "Unknown Error"
        viewModelScope.launch {
            validationEventChannel.send(ValidationEvent.Failed)
        }
    }

    override fun checkUser() {
        _taskState.value = TaskState.Loading
        viewModelScope.launch {
            val result = confirmEmailUseCase.execute(state.clientTokenData)
            result.handleResult(::success, ::failed)
        }
        _taskState.value = TaskState.Idle
    }

    override fun onEvent(event: ConfirmAccountFormEvent) {
        when (event) {
            ConfirmAccountFormEvent.Refresh -> checkUser()
            else -> {}
        }
    }

}
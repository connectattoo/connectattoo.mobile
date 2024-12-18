package br.com.connectattoo.ui.screen_app.account_manager.loginScreen

import androidx.lifecycle.ViewModel
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.util.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.receiveAsFlow

class FakeLoginViewModel : LoginViewModel() {
    override val message = MutableStateFlow("Mensagem de Teste")
    override var state: LoginFormState
        get() = LoginFormState()
        set(value) {}
    override val validationEventChannel = Channel<ValidationEvent>()
    override val validationEvents = emptyFlow<ValidationEvent>()
    override val taskState = MutableStateFlow<TaskState>(TaskState.Idle)


    override fun success(resultPostLogin: String) {
    }

    override fun failed(exception: Throwable?) {}

    override fun submitData() {
    }

    override fun enableButton(): Boolean {
        return true
    }

    override fun onEvent(event: LoginFormEvent) {
    }
}

abstract class LoginViewModel : ViewModel() {
    abstract var state: LoginFormState
    abstract val validationEventChannel: Channel<ValidationEvent>
    open val validationEvents: Flow<ValidationEvent>
        get() = validationEventChannel.receiveAsFlow()

    abstract val message: StateFlow<String>

    abstract val taskState: StateFlow<TaskState>

    abstract fun success(resultPostLogin: String)
    abstract fun failed(exception: Throwable?)
    abstract fun submitData()
    abstract fun enableButton(): Boolean
    abstract fun onEvent(event: LoginFormEvent)
}
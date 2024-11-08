package br.com.connectattoo.screen_app.account_manager.registerScreen

import br.com.connectattoo.states.TaskState
import br.com.connectattoo.util.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow


class FakeRegisterViewModel : RegisterViewModel() {
    override var state: RegisterFormState
        get() = RegisterFormState()
        set(value) {}
    override val validationEventChannel: Channel<ValidationEvent>
        get() = Channel<ValidationEvent>()
    override val message: StateFlow<String>
        get() = TODO("Not yet implemented")
    override val taskState: StateFlow<TaskState>
        get() = TODO("Not yet implemented")

    override fun onEvent(event: RegisterFormEvent) {
        TODO("Not yet implemented")
    }


}
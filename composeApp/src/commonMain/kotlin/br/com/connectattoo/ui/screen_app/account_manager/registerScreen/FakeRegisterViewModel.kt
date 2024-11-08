package br.com.connectattoo.ui.screen_app.account_manager.registerScreen

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

    override fun submitData() {
        TODO("Not yet implemented")
    }

    override fun onEvent(event: RegisterFormEvent) {
        TODO("Not yet implemented")
    }

    override fun enableButton(): Boolean {
        TODO("Not yet implemented")
    }

    override fun change(
        name: String?,
        lastName: String?,
        email: String?,
        phone: String?,
        password: String?,
        birthDate: String?,
        repeatedPassword: String?,
        privacy: Boolean?
    ) {
        TODO("Not yet implemented")
    }



}
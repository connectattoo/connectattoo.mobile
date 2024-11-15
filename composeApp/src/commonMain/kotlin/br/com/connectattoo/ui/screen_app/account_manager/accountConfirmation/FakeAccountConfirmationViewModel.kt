package br.com.connectattoo.ui.screen_app.account_manager.accountConfirmation

import br.com.connectattoo.states.TaskState
import br.com.connectattoo.util.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow

class FakeAccountConfirmationViewModel : AccountConfirmationViewModel() {


    override var state: ConfirmAccountFormState
        get() = TODO("Not yet implemented")
        set(value) {}
    override val validationEventChannel: Channel<ValidationEvent>
        get() = TODO("Not yet implemented")
    override val message: StateFlow<String>
        get() = TODO("Not yet implemented")
    override val taskState: StateFlow<TaskState>
        get() = TODO("Not yet implemented")

    override fun success(clientTokenData: String) {
        TODO("Not yet implemented")
    }

    override fun failed(exception: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun checkUser() {
        TODO("Not yet implemented")
    }

    override fun onEvent(event: ConfirmAccountFormEvent) {
        TODO("Not yet implemented")
    }

}
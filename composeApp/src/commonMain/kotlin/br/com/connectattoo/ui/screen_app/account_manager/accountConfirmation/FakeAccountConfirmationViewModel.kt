package br.com.connectattoo.ui.screen_app.account_manager.accountConfirmation

import androidx.lifecycle.ViewModel
import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.util.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

class FakeAccountConfirmationViewModel : AccountConfirmationViewModel() {
   /* abstract var state: RegisterFormState
    abstract val validationEventChannel: Channel<ValidationEvent>
    abstract val message: StateFlow<String>
    abstract val taskState: StateFlow<TaskState>

    open val validationEvents: Flow<ValidationEvent>
        get() = validationEventChannel.receiveAsFlow()

    abstract fun success(resultPostRegister: TokenData)
     abstract fun failed(exception: Throwable?)
    abstract fun submitData()

    abstract fun onEvent(event: RegisterFormEvent)

    abstract fun enableButton(): Boolean
    abstract fun change(
        name: String? = null,
        email: String? = null,
        password: String? = null,
        birthDate: String? = null,
        repeatedPassword: String? = null,
        privacy: Boolean? = null
    )*/
}
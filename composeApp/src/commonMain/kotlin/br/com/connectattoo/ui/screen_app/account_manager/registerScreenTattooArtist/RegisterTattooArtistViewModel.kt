package br.com.connectattoo.ui.screen_app.account_manager.registerScreenTattooArtist

import androidx.lifecycle.ViewModel
import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.util.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class RegisterTattooArtistViewModel : ViewModel() {
    abstract var state: RegisterTattooArtistFormState
    abstract val validationEventChannel: Channel<ValidationEvent>
    abstract val message: StateFlow<String>
    abstract val taskState: StateFlow<TaskState>

    open val validationEvents: Flow<ValidationEvent>
        get() = validationEventChannel.receiveAsFlow()

    abstract fun success(resultPostRegister: TokenData)
     abstract fun failed(exception: Throwable?)
    abstract fun submitData()

    abstract fun onEvent(event: RegisterTattooArtistFormEvent)

    abstract fun enableButton(): Boolean
    abstract fun change(
        name: String? = null,
        email: String? = null,
        password: String? = null,
        repeatedPassword: String? = null,
        zipCode: String? = null,
        street: String? = null,
        number: String? = null,
        city: String? = null,
        stateAddress: String? = null,
        birthDate: String? = null,
        privacy: Boolean? = null
    )
}
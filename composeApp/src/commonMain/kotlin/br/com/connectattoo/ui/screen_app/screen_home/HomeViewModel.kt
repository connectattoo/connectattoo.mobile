package br.com.connectattoo.ui.screen_app.screen_home

import androidx.lifecycle.ViewModel
import br.com.connectattoo.domain.model.TattooClientProfile
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.ui.screen_app.account_manager.loginScreen.LoginFormEvent
import br.com.connectattoo.ui.screen_app.account_manager.loginScreen.LoginFormState
import br.com.connectattoo.util.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class HomeViewModel: ViewModel() {

    abstract var state: HomeUserState
    abstract val validationEventChannel: Channel<ValidationEvent>
    open val validationEvents: Flow<ValidationEvent>
        get() = validationEventChannel.receiveAsFlow()

    abstract val message: StateFlow<String>

    abstract val taskState: StateFlow<TaskState>

    abstract fun success(tattooClientProfile: TattooClientProfile)
    abstract fun failed(exception: Throwable?)
    abstract fun getClientProfile()
    abstract fun enableButton(): Boolean
    abstract fun onEvent(event: LoginFormEvent)
}
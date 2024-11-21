package br.com.connectattoo.ui.screen_app.screens_apresentation.splashScreen

import androidx.lifecycle.ViewModel
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.util.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class SplashViewModel : ViewModel() {
    abstract val validationEventChannel: Channel<ValidationEvent>
    abstract val message: StateFlow<String>
    abstract val taskState: StateFlow<TaskState>

    open val validationEvents: Flow<ValidationEvent>
        get() = validationEventChannel.receiveAsFlow()

    abstract fun success(clientTokenData: String)
    abstract fun failed(exception: Throwable?)
    abstract fun checkUser()

    abstract fun onEvent(event: SplashFormEvent)

}
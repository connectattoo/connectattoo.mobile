package br.com.connectattoo.ui.screen_app.screen_home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import br.com.connectattoo.domain.model.TattooClientProfile
import br.com.connectattoo.domain.use_cases.client.GetClientProfileUseCase
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.ui.screen_app.account_manager.loginScreen.LoginFormEvent
import br.com.connectattoo.util.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModelImpl(
    private val getClientProfileUseCase: GetClientProfileUseCase
) : HomeViewModel() {
    override var state by mutableStateOf(HomeUserState())
    override val validationEventChannel = Channel<ValidationEvent>()
    override val validationEvents = validationEventChannel.receiveAsFlow()

    override val message: StateFlow<String> get() = setMessage
    private val setMessage = MutableStateFlow("")
    private val _taskState: MutableStateFlow<TaskState> = MutableStateFlow(TaskState.Idle)
    override val taskState: StateFlow<TaskState> = _taskState

    init {
        getClientProfile()
    }

    override fun success(tattooClientProfile: TattooClientProfile) {
        setMessage.value = tattooClientProfile.name.toString()
        viewModelScope.launch {
            val firstName = tattooClientProfile.name?.split(" ")?.get(0)
            state = state.copy(
                name = firstName ?: "error name",
                username = tattooClientProfile.username,
                birthDate = tattooClientProfile.birthDate,
                imageProfile = tattooClientProfile.imageProfile,
                tags = tattooClientProfile.tags,
                email = tattooClientProfile.email
            )
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    override fun failed(exception: Throwable?) {
        setMessage.value = exception?.message.toString()
        viewModelScope.launch { validationEventChannel.send(ValidationEvent.Failed) }
    }

    override fun getClientProfile() {
        _taskState.value = TaskState.Loading
        viewModelScope.launch {
            val result = getClientProfileUseCase.execute(Unit)
            result.handleResult(::success, ::failed)
            _taskState.value = TaskState.Idle
        }
    }

    override fun enableButton(): Boolean {
        return true
    }

    override fun onEvent(event: LoginFormEvent) {

    }
}
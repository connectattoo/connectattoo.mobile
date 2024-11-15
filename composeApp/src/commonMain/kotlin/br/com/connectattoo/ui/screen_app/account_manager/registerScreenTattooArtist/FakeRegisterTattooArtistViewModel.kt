package br.com.connectattoo.ui.screen_app.account_manager.registerScreenTattooArtist

import br.com.connectattoo.domain.model.TokenData
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.util.ValidationEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.StateFlow


class FakeRegisterTattooArtistViewModel : RegisterTattooArtistViewModel() {
    override var state: RegisterTattooArtistFormState
        get() = RegisterTattooArtistFormState()
        set(value) {}
    override val validationEventChannel: Channel<ValidationEvent>
        get() = Channel<ValidationEvent>()
    override val message: StateFlow<String>
        get() = TODO("Not yet implemented")
    override val taskState: StateFlow<TaskState>
        get() = TODO("Not yet implemented")

    override fun success(resultPostRegister: String) {
        TODO("Not yet implemented")
    }

    override fun failed(exception: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun submitData() {
        TODO("Not yet implemented")
    }

    override fun onEvent(event: RegisterTattooArtistFormEvent) {
        TODO("Not yet implemented")
    }

    override fun enableButton(): Boolean {
        TODO("Not yet implemented")
    }

    override fun change(
        name: String?,
        email: String?,
        password: String?,
        repeatedPassword: String?,
        zipCode: String? ,
        street: String? ,
        number: String? ,
        city: String? ,
        state: String?,
        birthDate: String?,
        privacy: Boolean?
    ) {
        TODO("Not yet implemented")
    }



}
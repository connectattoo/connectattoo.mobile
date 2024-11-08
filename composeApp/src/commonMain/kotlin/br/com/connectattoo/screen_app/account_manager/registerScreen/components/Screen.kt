package br.com.connectattoo.screen_app.account_manager.registerScreen.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import br.com.connectattoo.components.ButtonBackgroundPurple
import br.com.connectattoo.components.ButtonLigth
import br.com.connectattoo.components.DateInputText
import br.com.connectattoo.components.ImageLogo
import br.com.connectattoo.components.InputText
import br.com.connectattoo.components.mask.formatDate
import br.com.connectattoo.screen_app.account_manager.registerScreen.RegisterFormEvent
import br.com.connectattoo.screen_app.account_manager.registerScreen.RegisterViewModel
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun Screen(navController: NavController, viewModel: RegisterViewModel) {
    // val taskState by viewModel.taskState.collectAsState()
    val isDarkMode = isSystemInDarkTheme()
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .systemBarsPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.sdp, end = 20.sdp, top = 30.sdp, bottom = 50.sdp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
            ) {
                item {
                    ImageLogo()
                }

                item {
                    InputText(
                        titleText = "Nome",
                        placeholderText = "ex. João Silva",
                        textValue = viewModel.state.name,
                        textError = viewModel.state.nameError,
                        isError = !viewModel.state.nameError.isNullOrEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_name"),
                        onEvent = { it: String -> viewModel.onEvent(RegisterFormEvent.NameChanged(it)) }
                    )
                }
                item {
                    InputText(
                        titleText = "Email",
                        placeholderText = "nome@email.com",
                        textValue = viewModel.state.email,
                        textError = viewModel.state.emailError,
                        isError = !viewModel.state.emailError.isNullOrEmpty(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_email"),
                        onEvent = { it: String ->
                            viewModel.onEvent(
                                RegisterFormEvent.EmailChanged(
                                    it
                                )
                            )
                        }
                    )

                }
                item {
                    InputText(
                        titleText = "Senha",
                        placeholderText = "",
                        textValue = viewModel.state.password,
                        textError = viewModel.state.passwordError,
                        isError = !viewModel.state.passwordError.isNullOrEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_password"),
                        isPassword = true,
                        onEvent = { it: String ->
                            viewModel.onEvent(
                                RegisterFormEvent.PasswordChanged(
                                    it
                                )
                            )
                        },
                    )

                }
                item {
                    InputText(
                        titleText = "Confirmar Senha",
                        placeholderText = "",
                        textValue = viewModel.state.repeatedPassword,
                        textError = viewModel.state.repeatedPasswordError,
                        isError = !viewModel.state.repeatedPasswordError.isNullOrEmpty(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("input_confirmation_password"),
                        isPassword = true,
                        onEvent = { it: String ->
                            viewModel.onEvent(
                                RegisterFormEvent.ConfirmPasswordChanged(
                                    it
                                )
                            )
                        },
                    )

                }
                item {
                    DateInputText(
                        titleText = "Data de Nascimento",
                        placeholderText = "DD/MM/YYYY",
                        textValue = viewModel.state.birthDate,
                        textError = viewModel.state.birthDateError,
                        isError = !viewModel.state.birthDateError.isNullOrEmpty(),
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(150.sdp)
                            .padding(top = 10.sdp),
                        onEvent = { value: String ->
                            viewModel.onEvent(RegisterFormEvent.BirthDateChanged(value))
                        },
                        visualTransformation = { formatDate(it) }
                    )

                }
                item {

                    Spacer(modifier = Modifier.padding(top = 10.sdp))
                    ButtonBackgroundPurple(
                        submit = { navController.popBackStack() },
                        enableButton = true,
                        modifier = Modifier
                            .fillMaxSize(),
                        text = "Criar Conta",
                        textColor = Color.White
                    )

                }
                item {

                    Spacer(modifier = Modifier.padding(top = 10.sdp))
                    ButtonLigth(
                        submit = { navController.popBackStack() },
                        enableButton = true,
                        modifier = Modifier
                            .fillMaxSize(),
                        text = "Cancelar",
                    )

                }


            }
        }
    }
}
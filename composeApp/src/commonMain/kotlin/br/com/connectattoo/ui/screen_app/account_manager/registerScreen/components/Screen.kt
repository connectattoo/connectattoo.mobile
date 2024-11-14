package br.com.connectattoo.ui.screen_app.account_manager.registerScreen.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import br.com.connectattoo.states.TaskState
import br.com.connectattoo.ui.components.ButtonBackgroundPurple
import br.com.connectattoo.ui.components.ButtonLigth
import br.com.connectattoo.ui.components.DateInputText
import br.com.connectattoo.ui.components.ImageLogo
import br.com.connectattoo.ui.components.InputText
import br.com.connectattoo.ui.components.PasswordAlertText
import br.com.connectattoo.ui.components.PrivacyPolicyCheckbox
import br.com.connectattoo.ui.components.mask.formatDate
import br.com.connectattoo.ui.screen_app.account_manager.registerScreen.RegisterFormEvent
import br.com.connectattoo.ui.screen_app.account_manager.registerScreen.RegisterViewModel
import br.com.connectattoo.ui.theme.extendedColors
import network.chaintech.sdpcomposemultiplatform.sdp
import network.chaintech.sdpcomposemultiplatform.ssp

@Composable
fun Screen(
    navController: NavController,
    viewModel: RegisterViewModel,
    paddingValues: PaddingValues
) {
    val taskState by viewModel.taskState.collectAsState()
    val isDarkMode = isSystemInDarkTheme()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.sdp, end = 16.sdp, top = 16.sdp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
            ) {
                item {
                    ImageLogo()
                }

                item {
                    InputText(
                        titleText = "Nome Completo",
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
                    if (!viewModel.state.passwordErrorMessages.isNullOrEmpty()) {
                        Text(
                            modifier = Modifier.padding(start = 6.sdp),
                            text = "Condições de Senha",
                            fontSize = 10.ssp,
                            fontWeight = FontWeight.Bold
                        )
                        viewModel.state.passwordErrorMessages?.forEach { validationMessage ->
                            val textColor = when {
                                validationMessage.isValid -> MaterialTheme.extendedColors.green
                                else -> MaterialTheme.colorScheme.error
                            }
                            PasswordAlertText(
                                textMessage = validationMessage.message,
                                modifier = Modifier.padding(
                                    top = 6.sdp,
                                    bottom = 6.sdp,
                                    start = 10.sdp
                                ),
                                textColor = textColor
                            )
                        }
                    }
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
                            .fillMaxWidth()
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
                    Spacer(modifier = Modifier.padding(top = 4.sdp))
                    PrivacyPolicyCheckbox(
                        valueChecked = viewModel.state.privacyPolicy,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("checkbox"),
                        onEvent = { it: Boolean ->
                            viewModel.onEvent(
                                RegisterFormEvent.PrivacyPolicyChanged(
                                    it
                                )
                            )
                        }
                    )
                }
                item {

                    Spacer(modifier = Modifier.padding(top = 10.sdp))
                    ButtonBackgroundPurple(
                        submit = { viewModel.onEvent(RegisterFormEvent.Submit) },
                        enableButton = true,
                        modifier = Modifier
                            .fillMaxSize(),
                        text = "Criar Conta",
                        textColor = Color.White,
                        isLoading = taskState is TaskState.Loading
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
                    Spacer(modifier = Modifier.padding(top = 10.sdp))

                }


            }
        }
    }
}
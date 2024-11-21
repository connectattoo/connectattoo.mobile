package br.com.connectattoo.ui.screen_app.account_manager.loginScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.connectattoo.ui.components.ImageLogo
import br.com.connectattoo.ui.components.InputText
import br.com.connectattoo.ui.components.ScaffoldCustom
import br.com.connectattoo.ui.screen_app.account_manager.loginScreen.LoginFormEvent
import br.com.connectattoo.ui.screen_app.account_manager.loginScreen.LoginViewModel
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun Screen(navController: NavController, viewModel: LoginViewModel) {
    Column {
        ScaffoldCustom(
            modifier = Modifier,
            showActions = true,
            shadowBelowTopBar = 0.dp,
            showButtonToReturn = false,
            isLoading = false,
            navigationUp = navController,
            showTopBar = false,
            showBottomBarNavigation = false,
            contentToUse = {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.sdp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Top,
                    contentPadding = it
                ) {
                    item {
                        Spacer(modifier = Modifier.height(30.sdp))
                        ImageLogo()
                    }
                    item {
                        Spacer(modifier = Modifier.height(100.sdp))
                        InputText(
                            titleText = "Email",
                            placeholderText = "nome@email.com",
                            textValue = viewModel.state.email,
                            textError = viewModel.state.emailError,
                            isError = !viewModel.state.emailError.isNullOrEmpty(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            modifier = Modifier.fillMaxWidth()
                                .testTag("input_email"),
                            onEvent = { it: String ->
                                viewModel.onEvent(
                                    LoginFormEvent.EmailChanged(
                                        it
                                    )
                                )
                            }
                        )

                    }
                    item {
                        InputText(
                            titleText = "Senha:",
                            placeholderText = "**********",
                            textValue = viewModel.state.password,
                            textError = viewModel.state.passwordError,
                            isError = !viewModel.state.passwordError.isNullOrEmpty(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("input_password"),
                            isPassword = true,
                            onEvent = { it: String ->
                                viewModel.onEvent(
                                    LoginFormEvent.PasswordChanged(
                                        it
                                    )
                                )
                            },
                        )

                    }
                    item {
                        Spacer(modifier = Modifier.height(50.sdp))
                        Footer(navController, viewModel)
                    }

                }


            })


    }
}

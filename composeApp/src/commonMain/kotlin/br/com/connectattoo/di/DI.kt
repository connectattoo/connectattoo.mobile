package br.com.connectattoo.di

import br.com.connectattoo.data.repository.AuthRepositoryImpl
import br.com.connectattoo.domain.repository.AuthRepository
import br.com.connectattoo.domain.repository.ValidationRepository
import br.com.connectattoo.domain.use_cases.auth.ConfirmEmailUseCase
import br.com.connectattoo.domain.use_cases.auth.RegisterClientUseCase
import br.com.connectattoo.domain.use_cases.auth.RegisterTattooArtistUseCase
import br.com.connectattoo.domain.util.ValidationRepositoryImpl
import br.com.connectattoo.ui.screen_app.account_manager.accountConfirmation.AccountConfirmationViewModel
import br.com.connectattoo.ui.screen_app.account_manager.accountConfirmation.AccountConfirmationViewModelImpl
import br.com.connectattoo.ui.screen_app.account_manager.loginScreen.FakeLoginViewModel
import br.com.connectattoo.ui.screen_app.account_manager.loginScreen.LoginViewModel
import br.com.connectattoo.ui.screen_app.account_manager.loginScreen.LoginViewModelImpl
import br.com.connectattoo.ui.screen_app.account_manager.registerScreen.RegisterViewModel
import br.com.connectattoo.ui.screen_app.account_manager.registerScreen.RegisterViewModelImpl
import br.com.connectattoo.ui.screen_app.account_manager.registerScreenTattooArtist.RegisterTattooArtistViewModel
import br.com.connectattoo.ui.screen_app.account_manager.registerScreenTattooArtist.RegisterTattooArtistViewModelImpl
import br.com.connectattoo.util.PreferencesHelper
import com.russhwolf.settings.Settings
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module {
    //UseCases
    factory { RegisterClientUseCase(get()) }
    factory { RegisterTattooArtistUseCase(get()) }
    factory { ConfirmEmailUseCase(get(), get()) }

    //viewModels
    viewModel<RegisterViewModel> { RegisterViewModelImpl(get(),get()) }
    viewModel<RegisterTattooArtistViewModel> { RegisterTattooArtistViewModelImpl(get(),get()) }
    viewModel<AccountConfirmationViewModel> { AccountConfirmationViewModelImpl(get()) }
    viewModel<LoginViewModel> { LoginViewModelImpl(get()) }

    //repository
    single<ValidationRepository> { ValidationRepositoryImpl() }
    single<AuthRepository> { AuthRepositoryImpl(get(), get(), get()) }

    single { "http://10.0.2.2:3000/api/v1" }
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
            install(Logging) {
                level = LogLevel.BODY
            }
        }
    }


    //Save data, multiplatform-settings
    single { Settings() }

    single { PreferencesHelper(settings = get()) }

}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule)
    }
}

package br.com.connectattoo.di

import br.com.connectattoo.domain.repository.ValidationRepository
import br.com.connectattoo.ui.screen_app.account_manager.registerScreen.RegisterViewModel
import br.com.connectattoo.ui.screen_app.account_manager.registerScreen.RegisterViewModelImpl
import br.com.connectattoo.domain.util.ValidationRepositoryImpl
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val appModule = module{
    //viewModels
    viewModel<RegisterViewModel> { RegisterViewModelImpl(get()) }

    //repository
    single<ValidationRepository> { ValidationRepositoryImpl() }
}

fun initKoin(config: KoinAppDeclaration? = null){
     startKoin {
         config?.invoke(this)
         modules(appModule)
     }
}
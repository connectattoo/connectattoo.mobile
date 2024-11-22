package br.com.connectattoo.di


import br.com.connectattoo.data.database.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val targetModule = module {
    single { getDatabaseBuilder() }
}

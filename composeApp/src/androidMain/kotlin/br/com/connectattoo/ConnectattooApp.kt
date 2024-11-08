package br.com.connectattoo

import android.app.Application
import br.com.connectattoo.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class ConnectattooApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@ConnectattooApp)
            androidLogger(Level.DEBUG)
        }
    }
}
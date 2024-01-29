package com.example.nba

import android.app.Application
import com.example.nba.di.apiModule
import com.example.nba.di.networkingModule
import com.example.nba.di.repositoryModule
import com.example.nba.di.useCaseModule
import com.example.nba.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.KoinApplication
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.Logger
import org.koin.core.logger.MESSAGE
import timber.log.Timber

/**
 * The main application class for the NBA app. This class initializes Koin and provides a custom logger
 * that uses Timber for logging.
 */
class NbaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // initialize Koin
        startKoin {
            timberLogger()
            androidContext(this@NbaApplication)
            modules(networkingModule)
            modules(apiModule)
            modules(repositoryModule)
            modules(useCaseModule)
            modules(viewModelModule)
        }
    }

    // Custom Koin logger that uses Timber for logging
    class KoinTimberLogger : Logger(Level.ERROR) {
        override fun display(
            level: Level,
            msg: MESSAGE,
        ) {
            val newLevel =
                when (level) {
                    Level.INFO -> android.util.Log.INFO
                    Level.DEBUG -> android.util.Log.DEBUG
                    Level.ERROR -> android.util.Log.ERROR
                    Level.NONE -> android.util.Log.VERBOSE
                    Level.WARNING -> android.util.Log.WARN
                }
            Timber.log(newLevel, msg)
        }
    }

    @OptIn(KoinInternalApi::class)
    fun KoinApplication.timberLogger(): KoinApplication {
        koin.setupLogger(KoinTimberLogger())
        return this
    }
}

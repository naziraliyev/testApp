package uz.gita.testapp.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this

        Timber.plant(Timber.DebugTree())

    }

    companion object {
        lateinit var instance: App
            private set
    }
}
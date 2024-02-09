package com.ovidiucristurean.groceries.android

import android.app.Application
import com.ovidiucristurean.groceries.di.createAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GroceriesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GroceriesApplication)
            modules(
                modules = createAppModule()
            )
        }
    }
}

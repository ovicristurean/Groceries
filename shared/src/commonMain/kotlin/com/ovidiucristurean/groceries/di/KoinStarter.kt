package com.ovidiucristurean.groceries.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            commonModule +
                    createAppModule()
        )
    }
}

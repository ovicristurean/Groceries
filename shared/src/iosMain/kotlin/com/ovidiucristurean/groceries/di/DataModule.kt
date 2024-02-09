package com.ovidiucristurean.groceries.di

import com.ovidiucristurean.groceries.data.DriverFactory
import org.koin.dsl.module

fun createDataModule() = module {

    single<DriverFactory> {
        DriverFactory()
    }
}

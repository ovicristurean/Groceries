package com.ovidiucristurean.groceries.di

import org.koin.core.module.Module

actual fun createAppModule(): List<Module> = buildList {
    add(commonModule)
    add(createDataModule())
    add(createClipboardModule())
}

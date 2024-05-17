package com.ovidiucristurean.groceries.di

import com.ovidiucristurean.groceries.ui.util.ClipboardSaver
import org.koin.core.module.Module
import org.koin.dsl.module

fun createClipboardModule(): Module = module {
    single<ClipboardSaver> { ClipboardSaver() }
}

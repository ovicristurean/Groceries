package com.ovidiucristurean.groceries.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.ovidiucristurean.groceries.GroceriesDatabase

actual class DriverFactory(
    private val context: Context
) {
    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(GroceriesDatabase.Schema, context, "groceries.db")
    }
}

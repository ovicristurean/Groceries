package com.ovidiucristurean.groceries.data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.ovidiucristurean.groceries.GroceriesDatabase

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(GroceriesDatabase.Schema, "groceries.db")
    }
}

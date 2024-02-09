package com.ovidiucristurean.groceries.data

import app.cash.sqldelight.db.SqlDriver
import com.ovidiucristurean.groceries.GroceriesDatabase

expect class DriverFactory {
    fun createDriver(): SqlDriver
}

fun createDatabase(driverFactory: DriverFactory): GroceriesDatabase {
    val driver = driverFactory.createDriver()
    val database = GroceriesDatabase(driver)

    return database
}

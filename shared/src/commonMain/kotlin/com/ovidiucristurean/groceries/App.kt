package com.ovidiucristurean.groceries

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import com.ovidiucristurean.groceries.ui.homescreen.HomeScreen

@Composable
fun App(
    darkTheme: Boolean,
) {

    GroceriesTheme(
        darkTheme = darkTheme
    ) {
        Navigator(screen = HomeScreen()) { navigator ->
            CurrentScreen()
        }
    }
}
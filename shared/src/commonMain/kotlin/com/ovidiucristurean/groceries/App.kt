package com.ovidiucristurean.groceries

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.ovidiucristurean.groceries.ui.screen.home.HomeScreen

@Composable
fun App(
    darkTheme: Boolean,
) {

    GroceriesTheme(
        darkTheme = darkTheme
    ) {
        Navigator(screen = HomeScreen()) { navigator ->
            SlideTransition(navigator)
        }
    }
}
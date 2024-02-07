package com.ovidiucristurean.groceries

import androidx.compose.runtime.Composable

@Composable
expect fun GroceriesTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
)

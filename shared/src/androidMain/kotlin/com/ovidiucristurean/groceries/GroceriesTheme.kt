package com.ovidiucristurean.groceries

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.ovidiucristurean.groceries.ui.theme.DarkColorScheme
import com.ovidiucristurean.groceries.ui.theme.LightColorScheme
import com.ovidiucristurean.groceries.ui.theme.Typography

@Composable
actual fun GroceriesTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content,
    )
}

package com.ovidiucristurean.groceries.ui.commonview

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun RecipeDescriptionView(
    modifier: Modifier,
    description: String,
    onDescriptionChanged: (String) -> Unit
) {
    TextField(
        modifier = modifier.height(120.dp),
        value = description,
        onValueChange = { newValue ->
            onDescriptionChanged(newValue)
        },
        label = { Text("Recipe description") },
        maxLines = 3
    )
}

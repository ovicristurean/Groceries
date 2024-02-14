package com.ovidiucristurean.groceries.ui.screen.addrecipe.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun RecipeItemView(
    ingredientName: String,
    quantity: String,
    measurementUnit: String,
    onIngredientNameChanged: (String) -> Unit,
    onQuantityChanged: (String) -> Unit,
    onMeasurementUnitChanged: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = ingredientName,
            onValueChange = { newValue ->
                onIngredientNameChanged(newValue)
            },
            label = { Text("Ingredient name") },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            maxLines = 1
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = quantity,
            onValueChange = { newValue ->
                onQuantityChanged(newValue)
            },
            label = { Text("Quantity") },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Decimal
            ),
            maxLines = 1
        )

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = measurementUnit,
            onValueChange = { newValue ->
                onMeasurementUnitChanged(newValue)
            },
            label = { Text("Measurement unit") },
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            maxLines = 1
        )
    }
}

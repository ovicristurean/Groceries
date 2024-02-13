package com.ovidiucristurean.groceries.ui.screen.addrecipe.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun RecipeItemView(
    ingredientName: String,
    quantity: Int?,
    measurementUnit: String,
    onIngredientNameChanged: (String) -> Unit,
    onQuantityChanged: (Int) -> Unit,
    onMeasurementUnitChanged: (String) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = ingredientName,
            onValueChange = { newValue ->
                onIngredientNameChanged(newValue)
            },
            label = { Text("Ingredient name") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = quantity?.toString() ?: "",
            onValueChange = { newValue ->
                onQuantityChanged(newValue.toInt())
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Quantity") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = measurementUnit,
            onValueChange = { newValue ->
                onMeasurementUnitChanged(newValue)
            },
            label = { Text("Measurement unit") }
        )
    }
}

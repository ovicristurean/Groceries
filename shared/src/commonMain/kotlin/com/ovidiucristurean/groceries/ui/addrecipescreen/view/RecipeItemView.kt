package com.ovidiucristurean.groceries.ui.addrecipescreen.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.ovidiucristurean.groceries.ui.addrecipescreen.state.RecipeItemUiState

@Composable
fun RecipeItemView(onRecipeItemChanged: (RecipeItemUiState) -> Unit) {
    var ingredientName by remember { mutableStateOf("") }
    var quantity by remember { mutableStateOf("") }
    var measurementUnit by remember { mutableStateOf("") }
    var recipeItem = RecipeItemUiState(
        ingredient = ingredientName,
        quantity = quantity.toIntOrNull() ?: 0                                                                                                                                                                                                                                                                                                                                                ,
        measurementUnit = measurementUnit
    )

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = ingredientName,
            onValueChange = { newValue ->
                ingredientName = newValue
                recipeItem = recipeItem.copy(
                    ingredient = newValue
                )
                onRecipeItemChanged(recipeItem)
            },
            label = { Text("Ingredient name") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = quantity,
            onValueChange = { newValue ->
                quantity = newValue
                recipeItem = recipeItem.copy(
                    quantity = newValue.toInt()
                )
                onRecipeItemChanged(recipeItem)
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Quantity") }
        )

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = measurementUnit,
            onValueChange = { newValue ->
                measurementUnit = newValue
                recipeItem = recipeItem.copy(
                    measurementUnit = newValue
                )
                onRecipeItemChanged(recipeItem)
            },
            label = { Text("Measurement unit") }
        )
    }
}

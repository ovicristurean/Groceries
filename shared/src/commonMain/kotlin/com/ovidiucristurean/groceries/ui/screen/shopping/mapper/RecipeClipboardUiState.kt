package com.ovidiucristurean.groceries.ui.screen.shopping.mapper

import com.ovidiucristurean.groceries.domain.model.RecipeModel

fun RecipeModel.toClipboardUiState(): String {
    val result = StringBuilder()
    ingredients.forEachIndexed { index, ingredient ->
        result.append("${ingredient.name} - ${ingredient.quantity} ${ingredient.measurementUnit}")
        if (index < ingredients.size - 1) {
            result.append("\n")
        }
    }

    return result.toString()
}

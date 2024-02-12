package com.ovidiucristurean.groceries.ui.shopping.mapper

import com.ovidiucristurean.groceries.domain.model.Ingredient
import com.ovidiucristurean.groceries.ui.shopping.state.IngredientUiState

fun List<Ingredient>.toUiState(numberOfPortions: Int, checkedIngredients: MutableSet<Int>) =
    mapIndexed { index, ingredient ->
        IngredientUiState(
            ingredientMessage = "${ingredient.name}, " +
                    "${(ingredient.quantity ?: 1) * numberOfPortions} " +
                    ingredient.measurementUnit,
            isChecked = checkedIngredients.contains(index)
        )
    }
        .toMutableList()

package com.ovidiucristurean.groceries.ui.screen.shopping.mapper

import com.ovidiucristurean.groceries.domain.model.Ingredient
import com.ovidiucristurean.groceries.ui.screen.shopping.state.IngredientUiState

fun List<Ingredient>.toUiState(numberOfPortions: Int, checkedIngredients: MutableSet<Int>) =
    mapIndexed { index, ingredient ->
        IngredientUiState(
            ingredientMessage = "${ingredient.name}, " +
                    "${ingredient.quantity * numberOfPortions} " +
                    ingredient.measurementUnit,
            isChecked = checkedIngredients.contains(index)
        )
    }
        .toMutableList()

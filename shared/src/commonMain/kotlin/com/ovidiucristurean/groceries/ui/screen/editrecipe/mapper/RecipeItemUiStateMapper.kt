package com.ovidiucristurean.groceries.ui.screen.editrecipe.mapper

import com.ovidiucristurean.groceries.domain.model.RecipeModel
import com.ovidiucristurean.groceries.ui.screen.addrecipe.state.RecipeItemUiState

fun RecipeModel.toIngredientsUiState() =
    ingredients.map { ingredient ->
        RecipeItemUiState(
            ingredient = ingredient.name,
            quantity = ingredient.quantity.toString(),
            measurementUnit = ingredient.measurementUnit
        )
    }

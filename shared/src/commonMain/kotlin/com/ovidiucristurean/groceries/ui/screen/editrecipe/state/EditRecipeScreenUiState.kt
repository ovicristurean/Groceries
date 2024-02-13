package com.ovidiucristurean.groceries.ui.screen.editrecipe.state

import com.ovidiucristurean.groceries.ui.screen.addrecipe.state.RecipeItemUiState

data class EditRecipeScreenUiState(
    val recipeName: String = "",
    val  addedIngredients: List<RecipeItemUiState> = emptyList()
)

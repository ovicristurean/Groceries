package com.ovidiucristurean.groceries.ui.addrecipescreen.state

data class AddRecipeScreenUiState(
    val recipeName: String = "",
    val currentIngredient: RecipeItemUiState = RecipeItemUiState(
        ingredient = "",
        quantity = null,
        measurementUnit = ""
    ),
    val addedIngredients: List<RecipeItemUiState> = emptyList()
)

package com.ovidiucristurean.groceries.ui.screen.addrecipe.state

data class AddRecipeScreenUiState(
    val recipeName: String = "",
    val currentIngredient: RecipeItemUiState = RecipeItemUiState(
        ingredient = "",
        quantity = "",
        measurementUnit = ""
    ),
    val addedIngredients: List<RecipeItemUiState> = emptyList(),
    val description: String = ""
)

sealed interface NavigationEvent {
    data object PopBackStack : NavigationEvent
}

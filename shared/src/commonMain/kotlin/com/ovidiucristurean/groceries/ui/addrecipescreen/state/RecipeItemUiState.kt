package com.ovidiucristurean.groceries.ui.addrecipescreen.state


data class RecipeItemUiState(
    val ingredient: String = "",
    val quantity: Int = 1,
    val measurementUnit: String = ""
)

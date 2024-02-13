package com.ovidiucristurean.groceries.ui.screen.shopping.state

data class ShoppingScreenUiState(
    val numberOfPortions: Int = 1,
    val ingredients: MutableList<IngredientUiState> = mutableListOf()
)

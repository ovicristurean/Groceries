package com.ovidiucristurean.groceries.ui.shopping.state

data class ShoppingScreenUiState(
    val numberOfPortions: Int = 1,
    val ingredients: MutableList<IngredientUiState> = mutableListOf()
)

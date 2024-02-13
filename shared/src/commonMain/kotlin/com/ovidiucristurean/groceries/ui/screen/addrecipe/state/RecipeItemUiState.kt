package com.ovidiucristurean.groceries.ui.screen.addrecipe.state


data class RecipeItemUiState(
    val ingredient: String = "",
    val quantity: Int?=null,
    val measurementUnit: String = ""
)

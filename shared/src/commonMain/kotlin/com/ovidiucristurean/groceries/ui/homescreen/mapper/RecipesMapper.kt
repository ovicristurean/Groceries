package com.ovidiucristurean.groceries.ui.homescreen.mapper

import com.ovidiucristurean.groceries.domain.model.RecipeModel
import com.ovidiucristurean.groceries.ui.homescreen.state.RecipeListItemUiState

fun List<RecipeModel>.toUiState() =
    map {
        RecipeListItemUiState(
            title = it.name
        )
    }
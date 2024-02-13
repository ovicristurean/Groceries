package com.ovidiucristurean.groceries.ui.screen.home.mapper

import com.ovidiucristurean.groceries.domain.model.RecipeModel
import com.ovidiucristurean.groceries.ui.screen.home.state.RecipeListItemUiState

fun List<RecipeModel>.toUiState() =
    map {
        RecipeListItemUiState(
            id = it.id,
            title = it.name
        )
    }
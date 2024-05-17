package com.ovidiucristurean.groceries.ui.screen.shopping

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.ovidiucristurean.groceries.domain.RecipeRepository
import com.ovidiucristurean.groceries.ui.screen.shopping.mapper.toClipboardUiState
import com.ovidiucristurean.groceries.ui.screen.shopping.mapper.toUiState
import com.ovidiucristurean.groceries.ui.screen.shopping.state.ShoppingScreenUiState
import com.ovidiucristurean.groceries.ui.util.ClipboardSaver
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ShoppingScreenModel(
    recipeId: Long,
    recipeRepository: RecipeRepository,
    private val clipboardSaver: ClipboardSaver
) : ScreenModel {

    private val portions = MutableStateFlow(1)
    private val checkedIngredients = MutableStateFlow(mutableSetOf<Int>())

    private var clipboardFormattedRecipe = ""

    private val _uiState = MutableStateFlow(ShoppingScreenUiState())
    val uiState: StateFlow<ShoppingScreenUiState> = combine(
        _uiState,
        recipeRepository.getRecipe(recipeId),
        portions,
        checkedIngredients
    ) { state, recipe, numberOfPortions, checkedIngredients ->
        state.copy(
            numberOfPortions = numberOfPortions,
            ingredients = recipe.ingredients.toUiState(numberOfPortions, checkedIngredients),
            description = recipe.description
        ).also {
            clipboardFormattedRecipe = recipe.toClipboardUiState()
        }
    }.stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000L), ShoppingScreenUiState())

    fun increasePortions() {
        portions.update { it + 1 }
    }

    fun decreasePortions() {
        if (portions.value > 1) {
            portions.update { it - 1 }
        }
    }

    fun toggleIngredient(ingredientIndex: Int, isSelected: Boolean) {
        checkedIngredients.update {
            val ingredientsCopy = it.toMutableSet()
            if (isSelected) {
                ingredientsCopy.add(ingredientIndex)
            } else {
                ingredientsCopy.remove(ingredientIndex)
            }
            ingredientsCopy
        }
    }

    fun saveToClipboard() {
        clipboardSaver.saveToClipboard(clipboardFormattedRecipe)
    }
}

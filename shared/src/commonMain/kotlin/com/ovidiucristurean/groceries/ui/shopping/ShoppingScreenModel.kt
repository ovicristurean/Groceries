package com.ovidiucristurean.groceries.ui.shopping

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.ovidiucristurean.groceries.domain.RecipeRepository
import com.ovidiucristurean.groceries.ui.shopping.mapper.toUiState
import com.ovidiucristurean.groceries.ui.shopping.state.ShoppingScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ShoppingScreenModel(
    private val recipeId: Long,
    private val recipeRepository: RecipeRepository
) : ScreenModel {

    private val portions = MutableStateFlow(1)
    private val checkedIngredients = MutableStateFlow(mutableSetOf<Int>())

    private val _uiState = MutableStateFlow(ShoppingScreenUiState())
    val uiState: StateFlow<ShoppingScreenUiState> = combine(
        _uiState,
        recipeRepository.getRecipe(recipeId),
        portions,
        checkedIngredients
    ) { state, recipe, numberOfPortions, checkedIngredients ->
        state.copy(
            numberOfPortions = numberOfPortions,
            ingredients = recipe.ingredients.toUiState(numberOfPortions, checkedIngredients)
        )
    }.stateIn(screenModelScope, SharingStarted.WhileSubscribed(5000L), ShoppingScreenUiState())

    fun increasePortions() {
        portions.update { it + 1 }
    }

    fun decreasePortions() {
        portions.update { it - 1 }
    }

    fun toggleIngredient(ingredientIndex: Int, isSelected: Boolean) {
        /*val ingredientList = uiState.value.recipe.ingredients
        ingredientList[ingredientIndex] = ingredientList[ingredientIndex].copy(
            isChecked = isSelected
        )
        _uiState.update {
            it.copy(
                recipe = it.recipe.copy(
                    ingredients = ingredientList
                )
            )
        }*/
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
}

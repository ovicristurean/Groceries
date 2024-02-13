package com.ovidiucristurean.groceries.ui.screen.addrecipe

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.ovidiucristurean.groceries.domain.RecipeRepository
import com.ovidiucristurean.groceries.domain.model.Ingredient
import com.ovidiucristurean.groceries.domain.model.RecipeModel
import com.ovidiucristurean.groceries.ui.screen.addrecipe.state.AddRecipeScreenUiState
import com.ovidiucristurean.groceries.ui.screen.addrecipe.state.NavigationEvent
import com.ovidiucristurean.groceries.ui.screen.addrecipe.state.RecipeItemUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddRecipeScreenModel(
    private val recipeRepository: RecipeRepository
) : ScreenModel {

    private val _uiState = MutableStateFlow(AddRecipeScreenUiState())
    val uiState: StateFlow<AddRecipeScreenUiState> = _uiState

    private val _navigationEvent = Channel<NavigationEvent>()
    val navigationEvent: Flow<NavigationEvent> = _navigationEvent.receiveAsFlow()

    fun onRecipeNameUpdated(newRecipeName: String) {
        _uiState.update {
            it.copy(
                recipeName = newRecipeName
            )
        }
    }

    fun onIngredientNameUpdated(newIngredientName: String) {
        _uiState.update {
            it.copy(
                currentIngredient = it.currentIngredient.copy(
                    ingredient = newIngredientName
                )
            )
        }
    }

    fun onQuantityUpdated(newQuantity: String) {
        _uiState.update {
            it.copy(
                currentIngredient = it.currentIngredient.copy(
                    quantity = newQuantity
                )
            )
        }
    }

    fun onMeasurementUnitUpdated(newMeasurementUnit: String) {
        _uiState.update {
            it.copy(
                currentIngredient = it.currentIngredient.copy(
                    measurementUnit = newMeasurementUnit
                )
            )
        }
    }

    fun addIngredient() {
        val currentIngredient = _uiState.value.currentIngredient
        _uiState.update {
            it.copy(
                currentIngredient = RecipeItemUiState(),
                addedIngredients = it.addedIngredients.toMutableList().also {
                    it.add(currentIngredient)
                }
            )
        }
    }

    fun addRecipe() {
        screenModelScope.launch {
            val isAddRecipeSuccessful = recipeRepository.addRecipe(
                RecipeModel(
                    id = -1L,
                    name = uiState.value.recipeName,
                    ingredients = uiState.value.addedIngredients.map { recipeItem ->
                        Ingredient(
                            name = recipeItem.ingredient,
                            quantity = recipeItem.quantity.toIntOrNull()?:0,
                            measurementUnit = recipeItem.measurementUnit
                        )
                    }
                )
            )

            if (isAddRecipeSuccessful) {
                _navigationEvent.send(NavigationEvent.PopBackStack)
            } else {

            }
        }
    }
}

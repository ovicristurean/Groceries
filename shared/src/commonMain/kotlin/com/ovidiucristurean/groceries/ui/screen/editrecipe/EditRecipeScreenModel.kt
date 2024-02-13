package com.ovidiucristurean.groceries.ui.screen.editrecipe

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.ovidiucristurean.groceries.domain.RecipeRepository
import com.ovidiucristurean.groceries.domain.model.Ingredient
import com.ovidiucristurean.groceries.domain.model.RecipeModel
import com.ovidiucristurean.groceries.ui.screen.editrecipe.mapper.toIngredientsUiState
import com.ovidiucristurean.groceries.ui.screen.editrecipe.state.EditRecipeScreenUiState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditRecipeScreenModel(
    private val recipeId: Long,
    private val recipeRepository: RecipeRepository
) : ScreenModel {

    private val _uiState = MutableStateFlow(EditRecipeScreenUiState())
    val uiState = _uiState.asStateFlow()

    private val _editRecipeChannel = Channel<Boolean>()
    val editRecipeChannel = _editRecipeChannel.receiveAsFlow()

    init {
        screenModelScope.launch {
            recipeRepository.getRecipe(recipeId).collectLatest { recipeModel ->
                _uiState.update {
                    it.copy(
                        recipeName = recipeModel.name,
                        addedIngredients = recipeModel.toIngredientsUiState()
                    )
                }
            }
        }
    }

    fun editRecipeName(name: String) {
        _uiState.update {
            it.copy(
                recipeName = name
            )
        }
    }

    fun editIngredientName(ingredientIndex: Int, name: String) {
        _uiState.update {
            val ingredientsCopy = it.addedIngredients.toMutableList()
            ingredientsCopy[ingredientIndex] = ingredientsCopy[ingredientIndex].copy(
                ingredient = name
            )
            it.copy(
                addedIngredients = ingredientsCopy
            )
        }
    }

    fun editQuantity(ingredientIndex: Int, quantity: String) {
        _uiState.update {
            val ingredientsCopy = it.addedIngredients.toMutableList()
            ingredientsCopy[ingredientIndex] = ingredientsCopy[ingredientIndex].copy(
                quantity = quantity
            )
            it.copy(
                addedIngredients = ingredientsCopy
            )
        }
    }

    fun editMeasurementUnit(ingredientIndex: Int, measurementUnit: String) {
        _uiState.update {
            val ingredientsCopy = it.addedIngredients.toMutableList()
            ingredientsCopy[ingredientIndex] = ingredientsCopy[ingredientIndex].copy(
                measurementUnit = measurementUnit
            )
            it.copy(
                addedIngredients = ingredientsCopy
            )
        }
    }

    fun confirmEditedRecipe() {
        screenModelScope.launch {
            _editRecipeChannel.trySend(
                recipeRepository.editRecipe(
                    recipe = RecipeModel(
                        id = recipeId,
                        name = uiState.value.recipeName,
                        ingredients = uiState.value.addedIngredients.map { ingredientUiState ->
                            Ingredient(
                                name = ingredientUiState.ingredient,
                                quantity = ingredientUiState.quantity.toIntOrNull() ?: 0,
                                measurementUnit = ingredientUiState.measurementUnit
                            )
                        }
                    )
                )
            )
        }
    }
}

package com.ovidiucristurean.groceries.ui.screen.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.ovidiucristurean.groceries.domain.RecipeRepository
import com.ovidiucristurean.groceries.ui.screen.home.mapper.toUiState
import com.ovidiucristurean.groceries.ui.screen.home.state.HomeScreenUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val recipeRepository: RecipeRepository
) : ScreenModel {

    private val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState: StateFlow<HomeScreenUiState> = _uiState

    init {
        screenModelScope.launch {
            recipeRepository.recipes.collect { recipeModels ->
                _uiState.update {
                    it.copy(
                        recipes = recipeModels.toUiState()
                    )
                }
            }
        }
    }
}
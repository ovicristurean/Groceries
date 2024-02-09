package com.ovidiucristurean.groceries.domain

import com.ovidiucristurean.groceries.Recipe
import com.ovidiucristurean.groceries.domain.model.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    val recipes: Flow<List<RecipeModel>>

    fun addRecipe(recipe: RecipeModel)

}

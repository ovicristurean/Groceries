package com.ovidiucristurean.groceries.domain

import com.ovidiucristurean.groceries.domain.model.RecipeModel
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    val recipes: Flow<List<RecipeModel>>

    suspend fun addRecipe(recipe: RecipeModel): Boolean

    fun getRecipe(recipeId: Long): Flow<RecipeModel>

    suspend fun editRecipe(recipe: RecipeModel): Boolean

    suspend fun deleteRecipe(recipeId: Long): Boolean

}

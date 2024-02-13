package com.ovidiucristurean.groceries.data

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import com.ovidiucristurean.groceries.GroceriesDatabase
import com.ovidiucristurean.groceries.domain.RecipeRepository
import com.ovidiucristurean.groceries.domain.model.Ingredient
import com.ovidiucristurean.groceries.domain.model.RecipeModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.encodeToJsonElement

class RecipeRepositoryImpl(
    private val groceriesDatabase: GroceriesDatabase
) : RecipeRepository {

    val queries = groceriesDatabase.recipeQueries
    override val recipes: Flow<List<RecipeModel>>
        get() = queries.getRecipes()
            .asFlow()
            .mapToList(Dispatchers.Default)
            .map { recipes ->
                recipes.map { recipe ->
                    RecipeModel(
                        id = recipe.id,
                        name = recipe.recipeName,
                        ingredients = Json.decodeFromString<List<Ingredient>>(recipe.ingredients)
                    )
                }
            }

    override suspend fun addRecipe(recipe: RecipeModel): Boolean {
        try {
            withContext(Dispatchers.Default) {
                queries.insertRecipe(
                    null,
                    recipe.name,
                    Json.encodeToJsonElement(recipe.ingredients).toString()
                )
            }
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            return false
        }

        return true
    }

    override fun getRecipe(recipeId: Long): Flow<RecipeModel> {
        return queries.getRecipeById(recipeId)
            .asFlow()
            .mapToOne(Dispatchers.Default)
            .map { recipe ->
                RecipeModel(
                    id = recipe.id,
                    name = recipe.recipeName,
                    ingredients = Json.decodeFromString<List<Ingredient>>(recipe.ingredients)
                )
            }
    }

    override suspend fun editRecipe(recipe: RecipeModel):Boolean {
        try {
            withContext(Dispatchers.Default) {
                queries.insertRecipe(
                    id = recipe.id,
                    recipeName = recipe.name,
                    ingredients = Json.encodeToJsonElement(recipe.ingredients).toString()
                )
            }
        } catch (e:Exception) {
            if (e is CancellationException) throw e
            return false
        }

        return true
    }

}

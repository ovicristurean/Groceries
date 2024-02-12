package com.ovidiucristurean.groceries.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeModel(
    val id: Long,
    val name: String,
    val ingredients: List<Ingredient>
)

@Serializable
data class Ingredient(
    val name: String,
    val quantity: Int?,
    val measurementUnit: String
)

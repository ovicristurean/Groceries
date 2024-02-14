package com.ovidiucristurean.groceries.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class RecipeModel(
    val id: Long,
    val name: String,
    val ingredients: List<Ingredient>,
    val description: String
)

@Serializable
data class Ingredient(
    val name: String,
    val quantity: Float,
    val measurementUnit: String
)

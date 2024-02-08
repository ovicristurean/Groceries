package com.ovidiucristurean.groceries.ui.homescreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ovidiucristurean.groceries.ui.AddRecipeScreen
import com.ovidiucristurean.groceries.ui.homescreen.state.RecipeListItemUiState
import com.ovidiucristurean.groceries.ui.homescreen.view.RecipeList

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navigator.push(AddRecipeScreen())
                    }
                ) {
                    Text("Add recipe")
                }
            }
        ) {
            RecipeList(
                recipes = listOf(
                    RecipeListItemUiState("Puiul crush"),
                    RecipeListItemUiState("Somon cu broccoli"),
                    RecipeListItemUiState("Orez cu spanac"),
                    RecipeListItemUiState("Paste cu branza si rosii cherry"),
                    RecipeListItemUiState("Lasagna cu spanac"),
                    RecipeListItemUiState("Lasagna cu dovlecel si ciuperci")
                )
            )
        }
    }
}

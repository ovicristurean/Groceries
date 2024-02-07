package com.ovidiucristurean.groceries.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.ovidiucristurean.groceries.ui.state.RecipeListItemUiState
import com.ovidiucristurean.groceries.ui.view.RecipeList

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {}
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

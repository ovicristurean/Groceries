package com.ovidiucristurean.groceries.ui.homescreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ovidiucristurean.groceries.ui.addrecipescreen.AddRecipeScreen
import com.ovidiucristurean.groceries.ui.homescreen.view.RecipeList
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeScreen : Screen, KoinComponent {

    @Composable
    override fun Content() {
        val viewModel: HomeScreenViewModel by inject()
        val navigator = LocalNavigator.currentOrThrow
        val uiState by viewModel.uiState.collectAsState()

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
            /*RecipeList(
                recipes = listOf(
                    RecipeListItemUiState("Puiul crush"),
                    RecipeListItemUiState("Somon cu broccoli"),
                    RecipeListItemUiState("Orez cu spanac"),
                    RecipeListItemUiState("Paste cu branza si rosii cherry"),
                    RecipeListItemUiState("Lasagna cu spanac"),
                    RecipeListItemUiState("Lasagna cu dovlecel si ciuperci")
                )
            )*/
            RecipeList(
                recipes = uiState.recipes
            )
        }
    }
}

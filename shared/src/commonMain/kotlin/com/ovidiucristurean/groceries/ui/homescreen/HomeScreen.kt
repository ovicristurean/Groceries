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
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ovidiucristurean.groceries.ui.addrecipescreen.AddRecipeScreen
import com.ovidiucristurean.groceries.ui.homescreen.view.RecipeList
import com.ovidiucristurean.groceries.ui.shopping.ShoppingScreen

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<HomeScreenViewModel>()
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
            RecipeList(
                recipes = uiState.recipes,
                onRecipeSelected = {
                    navigator.push(ShoppingScreen(it))
                }
            )
        }
    }
}

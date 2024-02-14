package com.ovidiucristurean.groceries.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ovidiucristurean.groceries.ui.screen.addrecipe.AddRecipeScreen
import com.ovidiucristurean.groceries.ui.screen.editrecipe.EditRecipeScreen
import com.ovidiucristurean.groceries.ui.screen.editrecipe.EditRecipeScreenModel
import com.ovidiucristurean.groceries.ui.screen.home.view.RecipeList
import com.ovidiucristurean.groceries.ui.screen.shopping.ShoppingScreen

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
                    Text(
                        modifier = Modifier.padding(4.dp),
                        text = "Add recipe"
                    )
                }
            }
        ) {
            RecipeList(
                recipes = uiState.recipes,
                onRecipeSelected = {
                    navigator.push(ShoppingScreen(it))
                },
                onEditRecipeSelected = {
                    navigator.push(EditRecipeScreen(it))
                },
                onDeleteRecipeSelected = {
                    viewModel.deleteRecipe(it)
                }
            )
        }
    }
}

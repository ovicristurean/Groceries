package com.ovidiucristurean.groceries.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import com.ovidiucristurean.groceries.ui.addrecipescreen.state.RecipeItemUiState
import com.ovidiucristurean.groceries.ui.addrecipescreen.view.RecipeItemView
import com.ovidiucristurean.groceries.ui.commonview.ItemSpacer

class AddRecipeScreen : Screen {

    @Composable
    override fun Content() {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        )
        {
            val recipeItems = remember {
                mutableStateListOf<RecipeItemUiState>()
            }
            AddRecipeSection(modifier = Modifier.fillMaxSize().weight(1f)) { recipeItem ->
                recipeItems.add(recipeItem)
            }

            ItemSpacer()

            RecipeResultSection(
                modifier = Modifier.fillMaxSize().weight(1f),
                recipeItems = recipeItems
            )
        }
    }
}

@Composable
private fun AddRecipeSection(
    modifier: Modifier = Modifier,
    onRecipeItemConfirmed: (RecipeItemUiState) -> Unit
) {
    var currentRecipeItem by remember { mutableStateOf(RecipeItemUiState()) }
    Column(
        modifier = modifier
    ) {
        RecipeItemView { updatedItem ->
            currentRecipeItem = updatedItem
        }

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                onRecipeItemConfirmed(currentRecipeItem)
            }) {
            Text("Add to recipe")
        }
    }
}

@Composable
private fun RecipeResultSection(
    modifier: Modifier = Modifier,
    recipeItems: List<RecipeItemUiState>
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LazyColumn {
            items(recipeItems) { recipeItem ->
                Text(
                    text = "${recipeItem.ingredient}; ${recipeItem.quantity} ${recipeItem.measurementUnit}"
                )
            }


            item {
                Button(
                    onClick = {
                        //save recipe
                    }
                ) {
                    Text("Confirm recipe")
                }
            }
        }
    }
}

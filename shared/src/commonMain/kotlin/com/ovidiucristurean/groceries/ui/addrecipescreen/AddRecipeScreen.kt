package com.ovidiucristurean.groceries.ui.addrecipescreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ovidiucristurean.groceries.ui.addrecipescreen.state.NavigationEvent
import com.ovidiucristurean.groceries.ui.addrecipescreen.state.RecipeItemUiState
import com.ovidiucristurean.groceries.ui.addrecipescreen.view.RecipeItemView
import com.ovidiucristurean.groceries.ui.commonview.ItemSpacer
import kotlinx.coroutines.flow.collectLatest

class AddRecipeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel = getScreenModel<AddRecipeScreenModel>()
        val uiState by viewModel.uiState.collectAsState()

        LaunchedEffect(Unit) {
            viewModel.navigationEvent.collectLatest {
                when (it) {
                    NavigationEvent.PopBackStack -> {
                        navigator.pop()
                    }
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(100.dp)
                    .background(MaterialTheme.colorScheme.inversePrimary),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Recipe name")
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = uiState.recipeName,
                    onValueChange = { newValue ->
                        viewModel.onRecipeNameUpdated(newValue)
                    },
                    label = { Text("Recipe name") },
                    maxLines = 1
                )
            }

            AddIngredientSection(
                modifier = Modifier.fillMaxSize().weight(1f),
                ingredientName = uiState.currentIngredient.ingredient,
                quantity = uiState.currentIngredient.quantity,
                measurementUnit = uiState.currentIngredient.measurementUnit,
                onIngredientNameChanged = { ingredientName ->
                    viewModel.onIngredientNameUpdated(ingredientName)
                },
                onQuantityChanged = { quantity ->
                    viewModel.onQuantityUpdated(quantity)
                },
                onMeasurementUnitChanged = { measurementUnit ->
                    viewModel.onMeasurementUnitUpdated(measurementUnit)
                },
                onIngredientConfirmed = {
                    viewModel.addIngredient()
                }
            )
            ItemSpacer()

            RecipeResultSection(
                modifier = Modifier.fillMaxSize().weight(1f),
                recipeItems = uiState.addedIngredients,
                onRecipeConfirmed = {
                    viewModel.addRecipe()
                }
            )
        }
    }
}

@Composable
private fun AddIngredientSection(
    modifier: Modifier = Modifier,
    ingredientName: String,
    quantity: Int?,
    measurementUnit: String,
    onIngredientNameChanged: (String) -> Unit,
    onQuantityChanged: (Int) -> Unit,
    onMeasurementUnitChanged: (String) -> Unit,
    onIngredientConfirmed: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        RecipeItemView(
            ingredientName = ingredientName,
            quantity = quantity,
            measurementUnit = measurementUnit,
            onIngredientNameChanged = { ingredientName ->
                onIngredientNameChanged(ingredientName)
            },
            onQuantityChanged = { quantity ->
                onQuantityChanged(quantity)
            },
            onMeasurementUnitChanged = { measurementUnit ->
                onMeasurementUnitChanged(measurementUnit)
            }
        )

        Button(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = {
                onIngredientConfirmed()
            }) {
            Text("Add to recipe")
        }
    }
}

@Composable
private fun RecipeResultSection(
    modifier: Modifier = Modifier,
    recipeItems: List<RecipeItemUiState>,
    onRecipeConfirmed: () -> Unit
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
                    onClick = onRecipeConfirmed
                ) {
                    Text("Confirm recipe")
                }
            }
        }
    }
}
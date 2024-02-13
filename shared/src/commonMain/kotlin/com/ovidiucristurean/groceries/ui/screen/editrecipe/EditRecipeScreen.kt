package com.ovidiucristurean.groceries.ui.screen.editrecipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.ovidiucristurean.groceries.ui.commonview.RecipeDescriptionView
import com.ovidiucristurean.groceries.ui.screen.addrecipe.state.RecipeItemUiState
import kotlinx.coroutines.launch
import org.koin.core.parameter.parametersOf

class EditRecipeScreen(
    private val recipeId: Long
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val viewModel =
            getScreenModel<EditRecipeScreenModel>(parameters = { parametersOf(recipeId) })
        val uiState by viewModel.uiState.collectAsState()
        rememberCoroutineScope().launch {
            viewModel.editRecipeChannel.collect { editSuccess ->
                if (editSuccess) {
                    navigator.pop()
                }
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.recipeName,
                onValueChange = { newValue ->
                    viewModel.editRecipeName(newValue)
                },
                label = { Text("Recipe name") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            RecipeDescriptionView(
                modifier = Modifier.fillMaxWidth(),
                description = uiState.description,
                onDescriptionChanged = { newValue ->
                    viewModel.editDescription(newValue)
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(32.dp)
            ) {
                item {
                    Text(
                        text = "Ingredients:",
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                itemsIndexed(uiState.addedIngredients) { index, ingredient ->
                    IngredientView(
                        modifier = Modifier.fillMaxWidth(),
                        ingredient = ingredient,
                        onIngredientNameChanged = { newIngredientName ->
                            viewModel.editIngredientName(index, newIngredientName)
                        },
                        onQuantityChanged = { newQuantity ->
                            viewModel.editQuantity(index, newQuantity)
                        },
                        onMeasurementUnitChanged = { newMeasurementUnit ->
                            viewModel.editMeasurementUnit(index, newMeasurementUnit)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                modifier = Modifier.fillMaxWidth(1 / 2f)
                    .padding(vertical = 16.dp)
                    .height(75.dp),
                onClick = {
                    viewModel.confirmEditedRecipe()
                }
            ) {
                Text("Confirm")
            }
        }
    }

    @Composable
    private fun IngredientView(
        modifier: Modifier,
        ingredient: RecipeItemUiState,
        onIngredientNameChanged: (String) -> Unit,
        onQuantityChanged: (String) -> Unit,
        onMeasurementUnitChanged: (String) -> Unit
    ) {
        Row(
            modifier = modifier
        ) {
            Column {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = ingredient.ingredient,
                    onValueChange = { newValue ->
                        onIngredientNameChanged(newValue)
                    },
                    label = { Text("Ingredient name") }
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    TextField(
                        modifier = Modifier.fillMaxWidth().weight(1f),
                        value = ingredient.quantity.toString(),
                        onValueChange = { newValue ->
                            onQuantityChanged(newValue)
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        label = { Text("Quantity") }
                    )
                    TextField(
                        modifier = Modifier.fillMaxWidth().weight(3f),
                        value = ingredient.measurementUnit,
                        onValueChange = { newValue ->
                            onMeasurementUnitChanged(newValue)
                        },
                        label = { Text("Measurement unit") }
                    )
                }
            }
        }
    }
}

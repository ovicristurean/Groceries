package com.ovidiucristurean.groceries.ui.screen.shopping

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.ovidiucristurean.groceries.ui.screen.shopping.state.IngredientUiState
import org.koin.core.parameter.parametersOf

class ShoppingScreen(
    private val recipeId: Long
) : Screen {

    @Composable
    override fun Content() {
        val viewModel = getScreenModel<ShoppingScreenModel>(parameters = { parametersOf(recipeId) })
        val uiState by viewModel.uiState.collectAsState()
        Column(
            modifier = Modifier.fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            AdjustPortionsView(
                modifier = Modifier.fillMaxWidth(),
                numberOfPortions = uiState.numberOfPortions,
                onAddClicked = {
                    viewModel.increasePortions()
                },
                onSubtractClicked = {
                    viewModel.decreasePortions()
                }
            )

            ChecklistView(
                modifier = Modifier.fillMaxWidth(),
                ingredients = uiState.ingredients,
                onIngredientToggled = { ingredientIndex, isChecked ->
                    viewModel.toggleIngredient(ingredientIndex, isChecked)
                }
            )
        }
    }

    @Composable
    private fun Modifier.circular(): Modifier =
        this.size(100.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.primary)

    @Composable
    private fun AdjustPortionsView(
        modifier: Modifier,
        numberOfPortions: Int,
        onAddClicked: () -> Unit,
        onSubtractClicked: () -> Unit
    ) {
        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement
                .spacedBy(
                    space = 16.dp,
                    alignment = Alignment.CenterHorizontally
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.circular().clickable {
                    onSubtractClicked()
                },
                painter = rememberVectorPainter(Icons.Default.ArrowDownward),
                contentDescription = null
            )
            Text(
                text = numberOfPortions.toString(),
                style = LocalTextStyle.current.merge(
                    TextStyle(
                        fontSize = 80.sp,
                    )
                )
            )
            Image(
                modifier = Modifier.circular().clickable {
                    onAddClicked()
                },
                painter = rememberVectorPainter(Icons.Default.ArrowUpward),
                contentDescription = null
            )
        }
    }

    @Composable
    fun ChecklistView(
        modifier: Modifier,
        ingredients: List<IngredientUiState>,
        onIngredientToggled: (Int, Boolean) -> Unit
    ) {
        LazyColumn(
            modifier = modifier
        ) {
            itemsIndexed(ingredients) { index, ingredient ->
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = ingredient.isChecked,
                        onCheckedChange = {
                            onIngredientToggled(index, it)
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = ingredient.ingredientMessage
                    )
                }
            }
        }
    }
}

package com.ovidiucristurean.groceries.ui.screen.home.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material.icons.filled.NoMeals
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.ovidiucristurean.groceries.ui.screen.home.state.RecipeListItemUiState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecipeList(
    recipes: List<RecipeListItemUiState>,
    onRecipeSelected: (Long) -> Unit,
    onEditRecipeSelected: (Long) -> Unit,
    onDeleteRecipeSelected: (Long) -> Unit
) {
    if (recipes.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.size(150.dp),
                painter = rememberVectorPainter(Icons.Default.NoMeals),
                contentDescription = null
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(recipes) { index, item ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(16.dp).height(200.dp)
                        .clickable {
                            onRecipeSelected(item.id)
                        },
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                            .padding(8.dp),
                        contentAlignment = Alignment.Center,

                        ) {
                        Text(
                            text = item.title,
                            textAlign = TextAlign.Center,
                        )

                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomEnd)
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Image(
                                modifier = Modifier
                                    .combinedClickable(
                                        onLongClick = {
                                            onDeleteRecipeSelected(item.id)
                                        }
                                    ) {},
                                painter = rememberVectorPainter(Icons.Default.Delete),
                                contentDescription = null
                            )

                            Image(
                                modifier = Modifier
                                    .clickable {
                                        onEditRecipeSelected(item.id)
                                    },
                                painter = rememberVectorPainter(Icons.Default.EditNote),
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        }
    }
}

package com.ovidiucristurean.groceries.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp
import com.ovidiucristurean.groceries.ui.state.RecipeListItemUiState

@Composable
fun RecipeList(recipes: List<RecipeListItemUiState>) {
    var spacerWidth by remember { mutableStateOf(0) }
    val predefinedColors = listOf(
        Color.Cyan,
        Color.Gray,
        Color.DarkGray,
        Color.LightGray
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(recipes) { index, item ->
            Card(
                modifier = Modifier.fillMaxWidth().padding(16.dp).height(200.dp)
                    .clickable { },
                colors =  CardDefaults.cardColors(
                    containerColor = predefinedColors[index % predefinedColors.size],
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center,

                    ) {
                    Text(item.title)
                }
            }

            Spacer(
                modifier = Modifier.fillMaxWidth().height(2.dp).background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.outline,
                            Color.Transparent
                        ),
                        startX = spacerWidth / 3f,
                    ),
                ).onGloballyPositioned { layoutCoordinates ->
                    spacerWidth = layoutCoordinates.size.width
                }
            )
        }
    }
}
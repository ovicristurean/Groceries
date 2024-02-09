package com.ovidiucristurean.groceries.ui.homescreen.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FileUpload
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import com.ovidiucristurean.groceries.ui.commonview.ItemSpacer
import com.ovidiucristurean.groceries.ui.homescreen.state.RecipeListItemUiState

@Composable
fun RecipeList(recipes: List<RecipeListItemUiState>) {
    val predefinedColors = listOf(
        Color.Cyan,
        Color.Gray,
        Color.DarkGray,
        Color.LightGray
    )

    if (recipes.isEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberVectorPainter(Icons.Default.FileUpload),
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
                        .clickable { },
                    colors = CardDefaults.cardColors(
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
                ItemSpacer()
            }
        }
    }
}

package com.ovidiucristurean.groceries.ui.commonview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.dp

@Composable
fun ItemSpacer(
    modifier: Modifier = Modifier,
) {
    var spacerWidth by remember { mutableStateOf(0) }
    Spacer(
        modifier = modifier.fillMaxWidth().height(2.dp).background(
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
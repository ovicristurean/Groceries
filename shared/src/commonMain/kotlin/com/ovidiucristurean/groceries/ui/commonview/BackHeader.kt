package com.ovidiucristurean.groceries.ui.commonview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.RenderVectorGroup
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

@Composable
fun BackHeader() {
    val navigator = LocalNavigator.currentOrThrow
    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        val image = remember { Icons.Default.ArrowBackIos }
        Image(
            modifier = Modifier.height(80.dp)
                .clickable {
                navigator.pop()
            },
            painter = rememberVectorPainter(
                defaultWidth = image.defaultWidth,
                defaultHeight = image.defaultHeight,
                viewportWidth = image.viewportWidth,
                viewportHeight = image.viewportHeight,
                name = image.name,
                tintColor = MaterialTheme.colorScheme.onBackground,
                tintBlendMode = image.tintBlendMode,
                autoMirror = image.autoMirror,
                content = { _, _ -> RenderVectorGroup(group = image.root) }
            ),
            contentDescription = null
        )
    }
}

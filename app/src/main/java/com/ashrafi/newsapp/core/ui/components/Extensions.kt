package com.ashrafi.newsapp.core.ui.components

import android.annotation.SuppressLint
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.addFocusedBorder(
    @SuppressLint("ComposeModifierWithoutDefault")
    borderColor: Color = MaterialTheme.colorScheme.outline,
    focusedBorderColor: Color = MaterialTheme.colorScheme.primary,
    borderStroke: Dp = 1.dp,
    focusedBorderStroke: Dp = 2.dp,
    isSelected: Boolean = false,
): Modifier {
    //Animate the border color
    val animatedBorderColor by animateColorAsState(
        targetValue = if (isSelected) focusedBorderColor else borderColor,
        animationSpec = tween(durationMillis = 500),
        label = "borderColor" // Adjust the duration as needed
    )

    val animatedBorderStroke by animateDpAsState(
        targetValue = if (isSelected) focusedBorderStroke else borderStroke,
        animationSpec = tween(durationMillis = 500),
        label = "borderStroke"
    )

    return this.then(
        Modifier
            .border(
                width = animatedBorderStroke,
                color = animatedBorderColor,
                shape = MaterialTheme.shapes.medium,
            )
            .clip(MaterialTheme.shapes.medium)
    )
}
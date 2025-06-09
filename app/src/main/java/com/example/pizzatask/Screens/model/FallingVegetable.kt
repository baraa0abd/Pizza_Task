package com.example.pizzatask.Screens.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.random.Random

data class FallingVegetable(
    val id: Int,
    val drawableRes: Int,
    val initialY: Dp = (-100).dp,
    val finalY: Dp = Random.nextInt(50, 250).dp,
    val initialX: Dp = Random.nextInt(-50, 50).dp,
    val finalX: Dp = Random.nextInt(-100, 100).dp,
    val initialRotation: Float = Random.nextFloat() * 360f,
    val finalRotation: Float = Random.nextFloat() * 360f
)
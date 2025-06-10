package com.example.pizzatask.Screens.model

import androidx.annotation.DrawableRes

data class Topping(
    @DrawableRes val imageRes: Int,
    val assetPath: String // e.g., "basil", "mushroom"
)
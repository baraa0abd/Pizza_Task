package com.example.pizzatask.Screens.AnimationCode

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pizzatask.R
import com.example.pizzatask.Screens.ToppingSpreadAnimation
import com.example.pizzatask.Screens.model.Topping

@Composable
fun BasilAnimation() {
    val basilToppings = listOf(
        Topping(imageRes = R.drawable.basil_1, assetPath = "basil", name = "basil"),
        Topping(imageRes = R.drawable.basil_2, assetPath = "basil", name = "basil"),
        Topping(imageRes = R.drawable.basil_3, assetPath = "basil", name = "basil"),
        Topping(imageRes = R.drawable.basil_4, assetPath = "basil", name = "basil"),
        Topping(imageRes = R.drawable.basil_5, assetPath = "basil", name = "basil"),
        Topping(imageRes = R.drawable.basil_6, assetPath = "basil", name = "basil"),
        Topping(imageRes = R.drawable.basil_7, assetPath = "basil", name = "basil"),
        Topping(imageRes = R.drawable.basil_8, assetPath = "basil", name = "basil"),
        Topping(imageRes = R.drawable.basil_9, assetPath = "basil", name = "basil"),
        Topping(imageRes = R.drawable.basil_10, assetPath = "basil", name = "basil")
    )
    ToppingSpreadAnimation(
        key = basilToppings,
        assetPath = "basil",
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}
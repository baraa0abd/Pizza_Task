package com.example.pizzatask.Screens.AnimationCode

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
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

    // 1. Extract the drawable resource IDs from your list.
    //    These IDs can be used directly by the Coil image loader.
    val imageIds = basilToppings.map { it.imageRes }

    // 2. Call the new animation function, passing the list of image IDs.
    ToppingSpreadAnimation(
        key = true, // Use a constant key to run the animation once
        imageModels = imageIds,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .offset(y=-180.dp)
    )
}
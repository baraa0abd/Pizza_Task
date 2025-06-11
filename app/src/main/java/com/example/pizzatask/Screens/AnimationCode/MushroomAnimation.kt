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
fun MushroomAnimation() {
    val mushroomToppings = listOf(
        Topping(imageRes = R.drawable.mushroom_1, assetPath = "mushroom", name = "mushroom"),
        Topping(imageRes = R.drawable.mushroom_2, assetPath = "mushroom", name = "mushroom"),
        Topping(imageRes = R.drawable.mushroom_3, assetPath = "mushroom", name = "mushroom"),
        Topping(imageRes = R.drawable.mushroom_4, assetPath = "mushroom", name = "mushroom"),
        Topping(imageRes = R.drawable.mushroom_5, assetPath = "mushroom", name = "mushroom"),
        Topping(imageRes = R.drawable.mushroom_6, assetPath = "mushroom", name = "mushroom"),
        Topping(imageRes = R.drawable.mushroom_7, assetPath = "mushroom", name = "mushroom"),
        Topping(imageRes = R.drawable.mushroom_8, assetPath = "mushroom", name = "mushroom"),
        Topping(imageRes = R.drawable.mushroom_9, assetPath = "mushroom", name = "mushroom"),
        Topping(imageRes = R.drawable.mushroom_10, assetPath = "mushroom", name = "mushroom")
    )

    val imageIds = mushroomToppings.map { it.imageRes }

    ToppingSpreadAnimation(
        key = true,
        imageModels = imageIds,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}
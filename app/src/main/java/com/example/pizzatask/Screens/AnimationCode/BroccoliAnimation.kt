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
fun BroccoliAnimation() {
    val broccoliToppings = listOf(
        Topping(imageRes = R.drawable.broccoli_1, assetPath = "broccoli", name = "broccoli"),
        Topping(imageRes = R.drawable.broccoli_2, assetPath = "broccoli", name = "broccoli"),
        Topping(imageRes = R.drawable.broccoli_3, assetPath = "broccoli", name = "broccoli"),
        Topping(imageRes = R.drawable.broccoli_4, assetPath = "broccoli", name = "broccoli"),
        Topping(imageRes = R.drawable.broccoli_5, assetPath = "broccoli", name = "broccoli"),
        Topping(imageRes = R.drawable.broccoli_6, assetPath = "broccoli", name = "broccoli"),
        Topping(imageRes = R.drawable.broccoli_7, assetPath = "broccoli", name = "broccoli"),
        Topping(imageRes = R.drawable.broccoli_8, assetPath = "broccoli", name = "broccoli"),
        Topping(imageRes = R.drawable.broccoli_9, assetPath = "broccoli", name = "broccoli"),
        Topping(imageRes = R.drawable.broccoli_10, assetPath = "broccoli", name = "broccoli")
    )

    val imageIds = broccoliToppings.map { it.imageRes }

    ToppingSpreadAnimation(
        key = true,
        imageModels = imageIds,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .offset(y=-180.dp)
    )
}
//package com.example.pizzatask.Screens.AnimationCode
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import com.example.pizzatask.R
//import com.example.pizzatask.Screens.ToppingSpreadAnimation
//import com.example.pizzatask.Screens.model.Topping
//
//@Composable
//fun OnionAnimation() {
//    val onionToppings = listOf(
//        Topping(imageRes = R.drawable.onion_1, assetPath = "onion", name = "onion"),
//        Topping(imageRes = R.drawable.onion_2, assetPath = "onion", name = "onion"),
//        Topping(imageRes = R.drawable.onion_3, assetPath = "onion", name = "onion"),
//        Topping(imageRes = R.drawable.onion_4, assetPath = "onion", name = "onion"),
//        Topping(imageRes = R.drawable.onion_5, assetPath = "onion", name = "onion"),
//        Topping(imageRes = R.drawable.onion_6, assetPath = "onion", name = "onion"),
//        Topping(imageRes = R.drawable.onion_7, assetPath = "onion", name = "onion"),
//        Topping(imageRes = R.drawable.onion_8, assetPath = "onion", name = "onion"),
//        Topping(imageRes = R.drawable.onion_9, assetPath = "onion", name = "onion"),
//        Topping(imageRes = R.drawable.onion_10, assetPath = "onion", name = "onion")
//    )onion
//
//    val imageIds = onionToppings.map { it.imageRes }
//
//    ToppingSpreadAnimation(
//        key = true,
//        imageModels = imageIds,
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    )
//}
//package com.example.pizzatask.Screens.AnimationCode
//
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
//fun SausageAnimation() {
//    val sausageToppings = listOf(
//        Topping(imageRes = R.drawable.sausage_1, assetPath = "sausage", name = "sausage"),
//        Topping(imageRes = R.drawable.sausage_2, assetPath = "sausage", name = "sausage"),
//        Topping(imageRes = R.drawable.sausage_3, assetPath = "sausage", name = "sausage"),
//        Topping(imageRes = R.drawable.sausage_4, assetPath = "sausage", name = "sausage"),
//        Topping(imageRes = R.drawable.sausage_5, assetPath = "sausage", name = "sausage"),
//        Topping(imageRes = R.drawable.sausage_6, assetPath = "sausage", name = "sausage"),
//        Topping(imageRes = R.drawable.sausage_7, assetPath = "sausage", name = "sausage"),
//        Topping(imageRes = R.drawable.sausage_8, assetPath = "sausage", name = "sausage"),
//        Topping(imageRes = R.drawable.sausage_9, assetPath = "sausage", name = "sausage"),
//        Topping(imageRes = R.drawable.sausage_10, assetPath = "sausage", name = "sausage")
//    )
//
//    val imageIds = sausageToppings.map { it.imageRes }
//
//    ToppingSpreadAnimation(
//        key = true,
//        imageModels = imageIds,
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    )
//}
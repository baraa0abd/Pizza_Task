package com.example.pizzatask

import AddToCartButton
import PizzaTopBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.pizzatask.Screens.MainPage.Capation
import com.example.pizzatask.Screens.MainPage.PizzaScreen
import com.example.pizzatask.Screens.MainPage.Plate
import com.example.pizzatask.Screens.MainPage.PriceText
import com.example.pizzatask.Screens.MainPage.VegSelection

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PizzaTopBar()
            Plate()
            PriceText()
            PizzaScreen()
            Capation()
            VegSelection()
            AddToCartButton()
        }
    }
}

@Composable
@Preview
fun PizzaTopBarPreview() {
    PizzaTopBar()
    Plate()
    PriceText()
    PizzaScreen()
    Capation()
    VegSelection()
    AddToCartButton()
}
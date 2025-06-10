package com.example.pizzatask

import AddToCartButton
import PizzaTopBar
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pizzatask.Screens.MainPage.Capation
import com.example.pizzatask.Screens.MainPage.PizzaScreen
import com.example.pizzatask.Screens.MainPage.PriceText
import com.example.pizzatask.Screens.ToppingSpreadAnimation
import com.example.pizzatask.Screens.VegSelection
import com.example.pizzatask.Screens.model.Topping
import com.example.pizzatask.ui.theme.PizzaTaskTheme
import com.example.pizzatask.Screens.MainPage.Plate as Plate1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Main()
        }
    }
}

@Composable
fun Main(){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        PizzaTopBar()
        Plate1()
        PriceText()
        PizzaScreen()
        Capation()
        AddToCartButton()
        PizzaTaskTheme {
            MainPizzaPage()
        }
    }
}

@Composable
fun MainPizzaPage() {
    // 1. DEFINE THE STATE
    val toppings = listOf(
        Topping(imageRes = R.drawable.basil_3, assetPath = "basil"),
        Topping(imageRes = R.drawable.mushroom, assetPath = "mushroom"),
        Topping(imageRes = R.drawable.onion_10, assetPath = "onion"),
        Topping(imageRes = R.drawable.broccoli_3, assetPath = "broccoli"),
        Topping(imageRes = R.drawable.sausage_5, assetPath = "sausage")
    )

    var selectedIndices by remember { mutableStateOf(emptySet<Int>()) }

    Box(modifier = Modifier.fillMaxSize()) {

        selectedIndices.forEach { index ->
            val selectedTopping = toppings[index]
            ToppingSpreadAnimation(
                key = "${selectedTopping.assetPath}_$index",
                assetPath = selectedTopping.assetPath
            )
        }

        // 4. USE THE VegSelection FUNCTION
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        ) {
            VegSelection(
                toppings = toppings,
                selectedIndices = selectedIndices,
                onItemSelected = { index ->
                    val newSelection = selectedIndices.toMutableSet()
                    if (index in newSelection) {
                        newSelection.remove(index)
                    } else {
                        newSelection.add(index)
                    }
                    selectedIndices = newSelection
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Main()
}
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pizzatask.Screens.AnimationCode.BasilAnimation
import com.example.pizzatask.Screens.AnimationCode.BroccoliAnimation
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
    val toppings = listOf(
        Topping(imageRes = R.drawable.basil_3, assetPath = "basil", name = "basil"),
        Topping(imageRes = R.drawable.mushroom, assetPath = "mushroom", name = "mushroom"),
        Topping(imageRes = R.drawable.onion_10, assetPath = "onion", name = "onion"),
        Topping(imageRes = R.drawable.broccoli_3, assetPath = "broccoli", name = "broccoli"),
        Topping(imageRes = R.drawable.sausage_5, assetPath = "sausage", name = "sausage")
    )

    var selectedIndices by remember { mutableStateOf(emptySet<Int>()) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Show animations for selected toppings
        selectedIndices.forEach { index ->
            val selectedTopping = toppings[index]
            when (selectedTopping.name) {
                "basil" -> BasilAnimation()
                "broccoli" -> BroccoliAnimation()
            }
        }

        // Bottom selection row
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 60.dp)
        ) {
            VegSelection(
                toppings = toppings,
                selectedIndices = selectedIndices,
                onItemSelected = { index ->
                    selectedIndices = if (index in selectedIndices) {
                        selectedIndices - index
                    } else {
                        selectedIndices + index
                    }
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
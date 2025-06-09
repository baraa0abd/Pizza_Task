package com.example.pizzatask.Screens.MainPage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzatask.Screens.AnimationCode.PizzaImageSwitcher

@Composable
fun SizeSelector(
    initialSelectedSize: String = "M",
    onSizeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val sizes = listOf("S", "M", "L")
    var selectedSize by remember { mutableStateOf(initialSelectedSize) }

    Row(
        modifier = modifier
            .offset(y = 490.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        sizes.forEach { size ->
            val isSelected = size == selectedSize
            Box(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = if (isSelected) Color.Red else Color.LightGray,
                        shape = CircleShape
                    )
                    .background(
                        color = if (isSelected) Color.Red.copy(alpha = 0.1f) else Color.White
                    )
                    .clickable {
                        selectedSize = size
                        onSizeSelected(size)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = size,
                    fontSize = 16.sp,
                    color = if (isSelected) Color.Red else Color.Black
                )
            }
        }
    }
}
@Composable
fun PizzaScreen() {
    // 1. State management for pizza size
    var pizzaSize by remember { mutableStateOf("M") } // Default to "M"

    // 2. Derived size in dp based on selected size
    val pizzaImageSize by remember(pizzaSize) {
        derivedStateOf {
            when (pizzaSize) {
                "S" -> 175.dp
                "M" -> 205.dp
                "L" -> 225.dp
                else -> 205.dp // Default to medium if unknown
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // 3. Display the pizza images with current size
        PizzaImageSwitcher(
            itemSize = pizzaImageSize,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .offset(y = -200.dp)
        )

        // 4. Size selector at the bottom
        SizeSelector(
            initialSelectedSize = pizzaSize, // Pass the current size
            onSizeSelected = { newSize ->
                pizzaSize = newSize // Update the state when user selects
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .offset(y = -750.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PizzaScreenPreview() {
    PizzaScreen()
}
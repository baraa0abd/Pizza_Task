package com.example.pizzatask.Screens.MainPage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SizeSelector(
    selectedSize: String,
    onSizeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val sizes = listOf("S", "M", "L")

    Row(
        modifier = modifier.fillMaxWidth()
            .offset(y = 460.dp),
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
                    .clickable { onSizeSelected(size) },
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

// Usage example:
@Composable
fun PizzaScreen() {
    var selectedSize by remember { mutableStateOf("M") }

    SizeSelector(
        selectedSize = selectedSize,
        onSizeSelected = { size -> selectedSize = size },
        modifier = Modifier.padding(16.dp)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PizzaScreenPreview() {
    PizzaScreen()
}
package com.example.pizzatask.Screens

// All necessary imports
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.pizzatask.Screens.model.Topping

@Composable
fun VegSelection(
    toppings: List<Topping>,
    selectedIndices: Set<Int>,
    onItemSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(toppings.size) { index ->
            val isSelected = index in selectedIndices
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) Color(0xFFE0F7E0) else Color.Transparent)
                    .border(
                        width = 2.dp,
                        color = if (isSelected) Color.Green else Color.LightGray,
                        shape = CircleShape
                    )
                    .clickable { onItemSelected(index) },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = toppings[index].imageRes),
                    contentDescription = "Topping ${toppings[index].assetPath}",
                    modifier = Modifier.size(60.dp)
                )
            }
        }
    }
}
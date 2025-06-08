package com.example.pizzatask.Screens.MainPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pizzatask.R

@Composable
fun VegSelection() {
    // A list to hold your drawable resources for the vegetable items
    val vegItems = listOf(
        R.drawable.basil_3, // Replace with your actual drawable names
        R.drawable.mushroom,
        R.drawable.onion_10,
        R.drawable.broccoli_3,
        R.drawable.sausage_5
    )

    var selectedItemIndex by remember { mutableStateOf(-1) }

    LazyRow(
        modifier = Modifier
            .offset(y=600.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(vegItems.size) { index ->
            val isSelected = selectedItemIndex == index
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(if (isSelected) Color(0xFFE0F7E0) else Color.Transparent) // Highlight if selected
                    .border(
                        width = 1.dp,
                        color = if (isSelected) Color.Green else Color.LightGray,
                        shape = CircleShape
                    )
                    .clickable {
                        selectedItemIndex = if (isSelected) -1 else index // Toggle selection
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = vegItems[index]),
                    contentDescription = "Vegetable Item ${index + 1}",
                    modifier = Modifier.size(60.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VegSelectionPreview() {
    VegSelection()
}
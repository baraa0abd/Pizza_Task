package com.example.pizzatask.Screens

// All necessary imports
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import com.example.pizzatask.R

@Composable
fun ToppingSelectionScreen() {
    val vegItems = listOf(
        R.drawable.basil_3,
        R.drawable.mushroom,
        R.drawable.onion_10,
        R.drawable.broccoli_3,
        R.drawable.sausage_5
    )
    val basilItemIndex = 0
    var selectedItemIndex by remember { mutableStateOf(-1) }

    Box(modifier = Modifier.fillMaxSize()) {
        // --- The Animation ---
        if (selectedItemIndex == basilItemIndex) {
            BasilSpreadFromAssets()
        }

        // --- The Selection UI ---
        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            VegSelection(
                vegItems = vegItems,
                selectedItemIndex = selectedItemIndex,
                onItemSelected = { index ->
                    selectedItemIndex = if (selectedItemIndex == index) -1 else index
                }
            )
        }
    }
}

@Composable
fun VegSelection(
    vegItems: List<Int>,
    selectedItemIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    LazyRow(
        modifier = Modifier

                .padding(16.dp)
            .offset(y=-50.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(vegItems.size) { index ->
            val isSelected = selectedItemIndex == index
            Box(
                modifier = Modifier
                    .offset(y=-50.dp)
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
                    painter = painterResource(id = vegItems[index]),
                    contentDescription = "Topping Item ${index + 1}",
                    modifier = Modifier.size(60.dp)
                )
            }
        }
    }
}

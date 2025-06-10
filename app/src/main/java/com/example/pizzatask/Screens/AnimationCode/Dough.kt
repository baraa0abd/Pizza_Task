package com.example.pizzatask.Screens.AnimationCode

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pizzatask.R
import com.example.pizzatask.Screens.model.BreadType
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.math.absoluteValue


@Composable
fun PizzaImageSwitcher(
    pizzas: List<BreadType>,
    modifier: Modifier = Modifier,
    onPizzaSelected: (Int) -> Unit // Callback to inform parent of the selected pizza index
) {
    val lazyListState = rememberLazyListState()
    val density = LocalDensity.current.density
    val itemWidthDp = 200.dp
    val itemWidthPx = itemWidthDp.value * density

    // This effect snaps the list to the center of an item when scrolling stops.
    LaunchedEffect(lazyListState.isScrollInProgress) {
        if (!lazyListState.isScrollInProgress) {
            val center = lazyListState.layoutInfo.viewportEndOffset / 2
            val closestItem = lazyListState.layoutInfo.visibleItemsInfo.minByOrNull {
                (it.offset + it.size / 2 - center).absoluteValue
            }
            if (closestItem != null) {
                lazyListState.animateScrollToItem(closestItem.index)
            }
        }
    }

    // This effect listens to the scroll position and calls the onPizzaSelected callback.
    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index ->
                onPizzaSelected(index)
            }
    }

    LazyRow(
        state = lazyListState,
        modifier = modifier .offset(y=-100.dp),
        contentPadding = PaddingValues(horizontal = (360.dp - itemWidthDp) / 2), // Center the first and last items
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(pizzas.size) { index ->
            val pizza = pizzas[index]
            val center = lazyListState.layoutInfo.viewportEndOffset / 2
            val itemInfo = lazyListState.layoutInfo.visibleItemsInfo.find { it.index == index }

            // Calculate scale and alpha based on distance from center
            val scale = if (itemInfo != null) {
                val itemCenter = itemInfo.offset + itemInfo.size / 2
                val distance = (itemCenter - center).absoluteValue
                (1f - (distance / (itemWidthPx * 2f)).coerceIn(0f, 0.4f))
            } else {
                0.6f // Default scale for items not visible
            }

            val alpha = if (itemInfo != null) {
                val itemCenter = itemInfo.offset + itemInfo.size / 2
                val distance = (itemCenter - center).absoluteValue
                (1f - (distance / (itemWidthPx * 1.5f)).coerceIn(0f, 0.6f))
            } else {
                0.4f // Default alpha for items not visible
            }

            Image(
                painter = painterResource(id = pizza.drawableRes),
                contentDescription = pizza.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(itemWidthDp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
            )
        }
    }
}


@Composable
fun BreadSelection(
    modifier: Modifier = Modifier,
    breadTypes: List<BreadType>,
    selectedIndex: Int, // Now receives the selected index
    onBreadSelected: (Int) -> Unit // Callback with index
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(breadTypes.size) { index ->
            val bread = breadTypes[index]
            val isSelected = (index == selectedIndex)

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable { onBreadSelected(index) } // Just call the callback
                    .border(
                        width = 1.dp,
                        color = if (isSelected) Color.Green else Color.LightGray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(vertical = 12.dp, horizontal = 16.dp)
            ) {
                Image(
                    painter = painterResource(id = bread.drawableRes),
                    contentDescription = bread.name,
                    modifier = Modifier.size(40.dp) // Smaller for a cleaner look
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = bread.name,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = if (isSelected) Color.Green else Color.Black
                )
            }
        }
    }
}


@Composable
fun PizzaBuilderScreen() {
    val pizzas = remember {
        listOf(
            BreadType("Neapolitan", R.drawable.bread_chesse), // Replace with your drawables
            BreadType("Margherita", R.drawable.breadred),
            BreadType("Pepperoni", R.drawable.breadredd),
            BreadType("Vegetarian", R.drawable.breadreddd),
            BreadType("Hawaiian", R.drawable.breadwhite)
        )
    }
    var selectedPizzaIndex by remember { mutableStateOf(0) }
    val lazyListState = rememberLazyListState()

    // Coroutine to sync the BreadSelection with the PizzaImageSwitcher
    LaunchedEffect(selectedPizzaIndex) {
        lazyListState.animateScrollToItem(selectedPizzaIndex)
    }

    Column(
        modifier = Modifier
            .offset(-100.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        PizzaImageSwitcher(
            pizzas = pizzas,
            modifier = Modifier.height(250.dp),
            onPizzaSelected = { index ->
                selectedPizzaIndex = index
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
        BreadSelection(
            breadTypes = pizzas,
            selectedIndex = selectedPizzaIndex,
            onBreadSelected = { index ->
                selectedPizzaIndex = index
            }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PizzaBuilderScreenPreview() {
    PizzaBuilderScreen()
}
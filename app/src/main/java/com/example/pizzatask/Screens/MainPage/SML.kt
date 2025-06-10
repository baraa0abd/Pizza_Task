package com.example.pizzatask.Screens.MainPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.unit.Dp
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
    imageSize: Dp,
    onPizzaSelected: (Int) -> Unit
) {
    val lazyListState = rememberLazyListState()
    val density = LocalDensity.current.density

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

    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { index ->
                onPizzaSelected(index)
            }
    }

    LazyRow(
        state = lazyListState,
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = (360.dp - imageSize) / 2f),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(pizzas.size) { index ->
            val pizza = pizzas[index]
            val center = lazyListState.layoutInfo.viewportEndOffset / 2
            val itemInfo = lazyListState.layoutInfo.visibleItemsInfo.find { it.index == index }
            val itemWidthPx = imageSize.value * density

            val scale = if (itemInfo != null) {
                val itemCenter = itemInfo.offset + itemInfo.size / 2
                val distance = (itemCenter - center).absoluteValue
                (1f - (distance / (itemWidthPx * 2f)).coerceIn(0f, 0.4f))
            } else { 0.6f }

            val alpha = if (itemInfo != null) {
                val itemCenter = itemInfo.offset + itemInfo.size / 2
                val distance = (itemCenter - center).absoluteValue
                (1f - (distance / (itemWidthPx * 1.5f)).coerceIn(0f, 0.6f))
            } else { 0.4f }

            Image(
                painter = painterResource(id = pizza.drawableRes),
                contentDescription = pizza.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(imageSize)
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
fun SizeSelector(
    initialSelectedSize: String = "M",
    onSizeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val sizes = listOf("S", "M", "L")
    var selectedSize by remember(initialSelectedSize) { mutableStateOf(initialSelectedSize) }

    Row(
        modifier = modifier
            .offset(y=-250.dp)
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
                        color = if (isSelected) Color.Red.copy(alpha = 0.1f) else Color.Transparent
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
                    color = if (isSelected) Color.Red else Color.Black,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}


@Composable
fun PizzaScreen() {
    var pizzaSize by remember { mutableStateOf("M") }
    var selectedPizzaIndex by remember { mutableStateOf(0) }

    val pizzaImageSize = remember(pizzaSize) {
        derivedStateOf {
            when (pizzaSize) {
                "S" -> 180.dp
                "M" -> 220.dp
                "L" -> 260.dp
                else -> 220.dp
            }
        }
    }

    val pizzas = remember {
        listOf(
            // FIXED: Removed the explicit package path to use the local BreadType
            BreadType("Neapolitan", R.drawable.bread_chesse),
            BreadType("Margherita", R.drawable.breadred),
            BreadType("Pepperoni", R.drawable.breadredd),
            BreadType("Vegetarian", R.drawable.breadreddd),
            BreadType("Hawaiian", R.drawable.breadwhite)
        )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .offset(y=-120.dp)
                .fillMaxSize()
                .padding(bottom = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            PizzaImageSwitcher(
                pizzas = pizzas,
                imageSize = pizzaImageSize.value,
                onPizzaSelected = { index ->
                    selectedPizzaIndex = index
                }
            )
        }

        SizeSelector(
            initialSelectedSize = pizzaSize,
            onSizeSelected = { newSize ->
                pizzaSize = newSize
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 48.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PizzaScreenPreview() {
    PizzaScreen()
}
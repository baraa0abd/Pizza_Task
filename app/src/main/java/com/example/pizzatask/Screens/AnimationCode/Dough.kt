package com.example.pizzatask.Screens.AnimationCode

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.pizzatask.R
@Composable
fun PizzaImageSwitcher(
    modifier: Modifier = Modifier,
    images: List<Int> = listOf(
        R.drawable.bread_chesse,
        R.drawable.breadred,
        R.drawable.breadredd,
        R.drawable.breadreddd,
        R.drawable.breadwhite
    ),
    itemSize: Dp = 205.dp, // Default to medium size
    spacing: Dp = 165.dp,
    verticalOffset: Dp = 60.dp,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp)
) {
    LazyRow(
        modifier = modifier
            .padding(75.dp)
            .height(itemSize)
            .offset(y = verticalOffset),
        contentPadding = contentPadding,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        items(images, key = { it }) { imageRes ->
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "Pizza image variant",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(itemSize)
                    .aspectRatio(1f)
                    .clip(CircleShape)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PizzaImageSwitcherPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        PizzaImageSwitcher(
            modifier = Modifier.fillMaxWidth(),
            spacing = 80.dp, // Reduced spacing for preview
            verticalOffset = 100.dp // Adjusted for preview
        )
    }
}

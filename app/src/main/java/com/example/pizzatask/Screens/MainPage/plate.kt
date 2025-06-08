package com.example.pizzatask.Screens.MainPage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pizzatask.R

@Composable
fun Plate(){
    Box(
        modifier = Modifier
            .offset(y=-175.dp)
            .fillMaxSize() // Fill the entire available space
            .padding(56.dp),
        contentAlignment = Alignment.Center // Center the content
    ) {
        Image(
            painter = painterResource(id = R.drawable.plate),
            contentDescription = "A dinner plate" // Add content description for accessibility

        )
    }

}

@Preview
@Composable
fun PlatePreview() {
    Plate()
}
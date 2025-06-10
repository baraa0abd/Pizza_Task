package com.example.pizzatask.Screens.VegSpread

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.io.IOException

// This is much more complex!
@Composable
fun BasilSpreadFromAssets() {
    val context = LocalContext.current
    val assetManager = context.assets

    // You have to build the list of filenames manually
    val imageFileNames = (1..10).map { "basil/basil_$it.png" }

    // Then load each one from the assets folder
    val basil_drawables = imageFileNames.mapNotNull { fileName ->
        try {
            val inputStream = assetManager.open(fileName)
            Drawable.createFromStream(inputStream, null)
        } catch (e: IOException) {
            null // Handle error: file not found
        }
    }

    // Now you have a list of Drawables, but this is less efficient
    // and harder to use with Jetpack Compose Image composable.
}
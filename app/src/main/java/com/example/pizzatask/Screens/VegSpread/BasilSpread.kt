package com.example.pizzatask.Screens.VegSpread

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.io.IOException

@Composable
fun BasilSpreadFromAssets() {
    val context = LocalContext.current
    val assetManager = context.assets
    val imageFileNames = (1..10).map { "basil/basil_$it.png" }
    val basil_drawables = imageFileNames.mapNotNull { fileName ->
        try {
            val inputStream = assetManager.open(fileName)
            Drawable.createFromStream(inputStream, null)
        } catch (e: IOException) {
            null
        }
    }
}


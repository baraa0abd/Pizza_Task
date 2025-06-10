package com.example.pizzatask.Screens.VegSpread

import android.graphics.drawable.Drawable
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.io.IOException

@Composable
fun SausageFromAssets(): List<Drawable?> {
    val context = LocalContext.current
    val assetManager = context.assets
    val imageFileNames = (1..10).map { "sausage/sausage_$it.png" }
    return imageFileNames.mapNotNull { fileName ->
        try {
            val inputStream = assetManager.open(fileName)
            Drawable.createFromStream(inputStream, null)
        } catch (e: IOException) {
            null
        }
    }
}
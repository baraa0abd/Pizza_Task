package com.example.pizzatask.Screens

import android.graphics.drawable.Drawable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import java.io.IOException

@Composable
fun BasilSpreadFromAssets() {
    val context = LocalContext.current
    val assetManager = context.assets
    val basilDrawables = remember {
        (1..10).mapNotNull {
            try {
                val fileName = "basil/basil_$it.png"
                val inputStream = assetManager.open(fileName)
                Drawable.createFromStream(inputStream, null)
            } catch (e: IOException) {
                null
            }
        }
    }

    // Animation states
    val scale = remember { Animatable(2.5f) }
    val alpha = remember { Animatable(0f) }
    val translationX = remember { Animatable(0f) }
    val translationY = remember { Animatable(-300f) } // Start higher up

    // Animation effects
    LaunchedEffect(key1 = Unit) {
        alpha.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 500))
        scale.animateTo(targetValue = 1f, animationSpec = tween(durationMillis = 1000))
    }
    LaunchedEffect(key1 = Unit) {
        translationY.animateTo(
            targetValue = 150f, // Final Y position on the pizza
            animationSpec = tween(durationMillis = 1200)
        )
    }

    // The UI for the animated image
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center // Centers the basil in the screen
    ) {
        if (basilDrawables.isNotEmpty()) {
            Image(
                // Using the modern rememberAsyncImagePainter
                painter = rememberAsyncImagePainter(model = basilDrawables[0]),
                contentDescription = "Falling Basil Leaf",
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scale.value,
                        scaleY = scale.value,
                        alpha = alpha.value,
                        translationX = translationX.value,
                        translationY = translationY.value
                    )
            )
        }
    }
}
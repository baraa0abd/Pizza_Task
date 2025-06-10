package com.example.pizzatask.Screens

import android.graphics.drawable.Drawable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import java.io.IOException
import kotlin.random.Random

@Composable
fun ToppingSpreadAnimation(
    key: Any,
    assetPath: String
) {
    val context = LocalContext.current
    val assetManager = context.assets

    // This block loads your images. It must have code inside it.
    val toppingDrawables = remember(assetPath) {
        (1..10).mapNotNull {
            try {
                val fileName = "$assetPath/${assetPath}_$it.png"
                val inputStream = assetManager.open(fileName)
                Drawable.createFromStream(inputStream, null)
            } catch (e: IOException) {
                null
            }
        }
    }

    // This block creates the random values correctly.
    val randomValues = remember(key) {
        object {
            val targetX = Random.nextInt(-50, 51).toFloat()
            val targetY = Random.nextInt(100, 201).toFloat()
            val rotation = Random.nextDouble(-30.0, 30.0).toFloat()
            val delay = Random.nextLong(0, 201)
        }
    }

    val scale = remember { Animatable(2.5f) }
    val alpha = remember { Animatable(0f) }
    val translationX = remember { Animatable(0f) }
    val translationY = remember { Animatable(-300f) }
    val rotation = remember { Animatable(0f) }

    LaunchedEffect(key) {
        // Reset animations
        scale.snapTo(2.5f)
        alpha.snapTo(0f)
        translationY.snapTo(-300f)
        translationX.snapTo(0f)
        rotation.snapTo(0f)

        // Animate with unique values
        alpha.animateTo(1f, animationSpec = tween(300, delayMillis = randomValues.delay.toInt()))
        scale.animateTo(1f, animationSpec = tween(1000, delayMillis = randomValues.delay.toInt()))
        translationY.animateTo(randomValues.targetY, animationSpec = tween(1200, delayMillis = randomValues.delay.toInt()))
        translationX.animateTo(randomValues.targetX, animationSpec = tween(1200, delayMillis = randomValues.delay.toInt()))
        rotation.animateTo(randomValues.rotation, animationSpec = tween(1200, delayMillis = randomValues.delay.toInt()))
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (toppingDrawables.isNotEmpty()) {
            Image(
                painter = rememberAsyncImagePainter(model = toppingDrawables[0]),
                contentDescription = "$assetPath spread animation",
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = scale.value,
                        scaleY = scale.value,
                        alpha = alpha.value,
                        translationX = translationX.value,
                        translationY = translationY.value,
                        rotationZ = rotation.value
                    )
            )
        }
    }
}
package com.example.pizzatask.Screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ToppingSpreadAnimation(
    key: Any,
    assetPath: String,
    modifier: Modifier = Modifier
) {
    val toppingImageUris = remember(assetPath) {
        (1..10).map { index -> "file:///android_asset/$assetPath/${assetPath}_$index.png" }
    }

    // Generate unique random values for each instance based on the key
    val randomValues = remember(key) {
        object {
            val targetX = Random.nextInt(-50, 51).toFloat()
            val targetY = Random.nextInt(50, 101).toFloat()
            val rotation = Random.nextDouble(-30.0, 10.0).toFloat()
            val delay = Random.nextLong(0, 100)
            // Pick a random URI from our list
            val imageUri = toppingImageUris.random()
        }
    }

    // Animation controllers
    val scale = remember { Animatable(2.5f) }
    val alpha = remember { Animatable(0f) }
    val translationX = remember { Animatable(0f) }
    val translationY = remember { Animatable(-300f) }
    val rotation = remember { Animatable(0f) }

    // Animation sequence
    LaunchedEffect(key) {
        // Reset to initial state for re-triggering
        scale.snapTo(2.5f)
        alpha.snapTo(0f)
        translationY.snapTo(-300f)
        translationX.snapTo(0f)
        rotation.snapTo(0f)

        // --- 2. Run all animations concurrently using launch { ... } ---
        launch { alpha.animateTo(1f, animationSpec = tween(300, delayMillis = randomValues.delay.toInt())) }
        launch { scale.animateTo(1f, animationSpec = tween(1000, delayMillis = randomValues.delay.toInt())) }
        launch {
            translationY.animateTo(
                targetValue = randomValues.targetY,
                animationSpec = tween(1200, delayMillis = randomValues.delay.toInt())
            )
        }
        launch {
            translationX.animateTo(
                targetValue = randomValues.targetX,
                animationSpec = tween(1200, delayMillis = randomValues.delay.toInt())
            )
        }
        launch {
            rotation.animateTo(
                targetValue = randomValues.rotation,
                animationSpec = tween(1200, delayMillis = randomValues.delay.toInt())
            )
        }
    }

    // --- 3. Apply the caller's modifier directly for predictable layout ---
    Box(
        modifier = modifier, // The caller now controls the size and position
        contentAlignment = Alignment.Center
    ) {
        // Using AsyncImage for a slightly cleaner API
        AsyncImage(
            model = randomValues.imageUri,
            contentDescription = "$assetPath spread animation",
            modifier = Modifier
                .graphicsLayer( // graphicsLayer is the most performant way to animate
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


package com.example.pizzatask.Screens // Or wherever your animation files are

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ToppingSpreadAnimation(
    key: Any,
    imageModels: List<Any>,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.size(4.dp), contentAlignment = Alignment.Center) {
        // Loop through every image model and create a unique animation for each one.
        imageModels.forEachIndexed { index, model ->
            // We create a composite key to ensure each item's animation is unique
            // and can be re-triggered by the parent key.
            SingleToppingPieceAnimation(
                key = key to index,
                imageModel = model
            )
        }
    }
}

/**
 * Animates a single topping piece. This contains the logic from your
 * original ToppingSpreadAnimation function.
 */
@Composable
private fun SingleToppingPieceAnimation(
    key: Any,
    imageModel: Any
) {
    // Generate unique random values for each instance based on its unique key
    val randomValues = remember(key) {
        object {
            val targetX = Random.nextInt(-150, 151).toFloat() // Increased range for better spread
            val targetY = Random.nextInt(-100, 151).toFloat() // Increased range
            val rotation = Random.nextDouble(-45.0, 45.0).toFloat()
            val delay = Random.nextLong(0, 300) // Increased delay variance
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
        scale.snapTo(2.5f)
        alpha.snapTo(0f)
        translationY.snapTo(-300f)
        translationX.snapTo(0f)
        rotation.snapTo(0f)

        launch { alpha.animateTo(1f, animationSpec = tween(300, delayMillis = randomValues.delay.toInt())) }
        launch { scale.animateTo(1f, animationSpec = tween(1000, delayMillis = randomValues.delay.toInt())) }
        launch { translationY.animateTo(randomValues.targetY, animationSpec = tween(1200, delayMillis = randomValues.delay.toInt())) }
        launch { translationX.animateTo(randomValues.targetX, animationSpec = tween(1200, delayMillis = randomValues.delay.toInt())) }
        launch { rotation.animateTo(randomValues.rotation, animationSpec = tween(1200, delayMillis = randomValues.delay.toInt())) }
    }

    AsyncImage(
        model = imageModel,
        contentDescription = "Topping piece",
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
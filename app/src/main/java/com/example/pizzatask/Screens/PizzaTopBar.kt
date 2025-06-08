import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun PizzaTopBar() {
    Row(
        modifier = Modifier
            .offset(y=16.dp)
            .fillMaxWidth() // Row should span the full width
            .padding(horizontal = 16.dp, vertical = 8.dp), // Add padding
        horizontalArrangement = Arrangement.SpaceBetween, // Distribute space between items
        verticalAlignment = Alignment.CenterVertically // Center items vertically

    ) {
        // Back arrow icon
        IconButton(onClick = { /* Handle back button click */ }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }

        // Title text
        Text(
            text = "Pizza",
            style = MaterialTheme.typography.titleLarge
        )

        // Heart icon
        IconButton(onClick = { /* Handle favorite click */ }) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Favorite"
            )
        }
    }
}
@Preview
@Composable
fun PizzaTopBarPreview() {
    PizzaTopBar()

}
package ar.edu.unlam.mobile.scaffold.ui.components

// import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CircularButton(withColor: String, withText: String, onClick: () -> Unit) {
    val buttonShape = CircleShape
//    val hexWithAlpha = "FF$withColor"
    var hexColor = Color(android.graphics.Color.parseColor(withColor)).copy(alpha = 1f)
//    val color = Color(hexColor)

    Column {
        Surface(
            modifier = Modifier
                .size(56.dp)
                .clip(buttonShape)
                .background(hexColor)
                .clickable(onClick = onClick),
        ) {
            // Contenido del botón, como un ícono o texto
        }
        Text("$withText")
    }
}

@Composable
@Preview
fun CircularButtonPreview() {
    CircularButton(withColor = "#FFFAFF81", withText = "Insurance") {
    }
}

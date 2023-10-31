import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CategoryTextField(colorClick: Boolean, category: String, onCategoryChanged: (String) -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        Arrangement.Center,
    ) {
        TextField(
            value = category,
            onValueChange = { updatedCategory ->
                onCategoryChanged(updatedCategory) // Notificar a la pantalla CategoryScreen
            },
            placeholder = {
                Text(
                    text = "Nombre de la categoria",
                    fontSize = 15.sp,
                    color = if (!colorClick) Color.Gray else Color.Red,
                )
            },
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
                .padding(
                    start = 10.dp,
                    end = 10.dp,
                ),
        )
    }
}

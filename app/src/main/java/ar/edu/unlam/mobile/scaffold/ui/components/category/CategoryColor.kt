package ar.edu.unlam.mobile.scaffold.ui.components.category

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffold.data.transaction.models.ColorsCategory

// Funcion que recorre el enum de colores y agrega el color al Box
@Composable
fun CategoryColor(onColorClick: (ColorsCategory) -> Unit) {
    var selectedColor by remember { mutableStateOf<ColorsCategory?>(null) }
    Column() {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            ColorsCategory.values().forEach { color ->
                ColorBox(Color(android.graphics.Color.parseColor(color.colorHex)), selectedColor == color) {
                    selectedColor = color
                    onColorClick(color)
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

// Funcion captura el color y asigna el borde al Box clickeado
@Composable
fun ColorBox(color: Color, isSelected: Boolean, onColorClick: () -> Unit) {
    val borderColor = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color)
            .clickable {
                onColorClick()
            }
            .border(
                width = 2.dp,
                color = borderColor,
                shape = RoundedCornerShape(20.dp),
            ),
    )
}

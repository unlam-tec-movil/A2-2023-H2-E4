package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffold.data.category.Category
import ar.edu.unlam.mobile.scaffold.data.category.CategoryRepository

@Composable
fun ListItemRow(item: Category) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .background(color = item.color)
    ) {
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            Modifier
                .padding(5.dp)
                .width(130.dp)
                .fillMaxHeight()
        ) {
            Text(item.name, textAlign = TextAlign.Left, fontSize = 15.sp)
        }
        Column(
            Modifier
                .padding(5.dp)
                .width(100.dp)
                .fillMaxHeight()
        ) {
            Text("${porcentaje(item)}%", textAlign = TextAlign.Center, fontSize = 15.sp)
        }
        Column(
            Modifier
                .padding(5.dp)
                .width(100.dp)
                .fillMaxHeight()
        ) {
            Text("$${item.totalAmount}", textAlign = TextAlign.Right, fontSize = 15.sp)
        }
    }
}

fun porcentaje(item: Category): Int {
    return CategoryRepository.porcentaje(item)
}

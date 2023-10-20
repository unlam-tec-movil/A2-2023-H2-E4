package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffold.domain.models.PieChartInput

@Composable
fun Statistics(
    categoria: String,
    porcentaje: String,
    total: String,
    list: MutableList<PieChartInput>,
    color1: Color,
    color2: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color1)
            .padding(0.dp, 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = categoria, fontSize = 23.sp, color = Color.White)
        Text(text = porcentaje, fontSize = 23.sp, color = Color.White)
        Text(text = total, fontSize = 23.sp, color = Color.White)
    }
    ShowCategories(list, color2)
}

@Composable
fun ShowCategories(data: MutableList<PieChartInput>, color: Color) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .height(200.dp)
            .background(color = color)
    ) {
        items(data) { item ->
            ListItemRow(item)
        }
    }
}

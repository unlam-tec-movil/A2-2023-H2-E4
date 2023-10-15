package ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffold.data.category.Category
import ar.edu.unlam.mobile.scaffold.data.category.CategoryRepository
import ar.edu.unlam.mobile.scaffold.ui.components.Statistics

@Composable
fun ChartScreen() {
    Column(
        modifier = Modifier.background(color = Color.Black).fillMaxSize()
    ) {
        Body(categoria = "Categoría", porcentaje = "Porcentaje", total = "Total", list = CategoryRepository.order() as MutableList<Category>, Color.Blue, Color.LightGray)
    }
}

@Composable
fun Body(categoria: String, porcentaje: String, total: String, list: MutableList<Category>, color1: Color, color2: Color) {
    Column {
        Spacer(modifier = Modifier.height(250.dp))
        Statistics(categoria = categoria, porcentaje = porcentaje, total = total, list = list, color1, color2)
    }
}

package ar.edu.unlam.mobile.scaffold.ui.screens.ChartScreen

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
        Body(column1 = "Categoría", column2 = "Porcentaje", column3 = "Total", list = CategoryRepository.order() as MutableList<Category>, Color.Blue, Color.LightGray)
    }
}

@Composable
fun Body(column1: String, column2: String, column3: String, list: MutableList<Category>, color1: Color, color2: Color) {
    Column {
        Spacer(modifier = Modifier.height(250.dp))
        Statistics(column1 = column1, column2 = column2, column3 = column3, list = list, color1, color2)
    }
}
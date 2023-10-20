package ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffold.domain.models.PieChartInput
import ar.edu.unlam.mobile.scaffold.ui.components.PieChart
import ar.edu.unlam.mobile.scaffold.ui.components.Statistics

@Composable
fun ChartScreen() {
    Column(
        modifier = Modifier.background(color = Color.Black).fillMaxSize()
    ) {
        Body(categoria = "Categoría", porcentaje = "Porcentaje", total = "Total", list = listOfPieChartInputOrder() as MutableList<PieChartInput>, Color.Blue, Color.LightGray)
    }
}

@Composable
fun Body(categoria: String, porcentaje: String, total: String, list: MutableList<PieChartInput>, color1: Color, color2: Color) {
    Column {
        Column(
            modifier = Modifier.height(300.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            PieChart(
                data = listOfPieChartInput()
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Statistics(categoria = categoria, porcentaje = porcentaje, total = total, list = list, color1, color2)
    }
}

fun listOfPieChartInput(): List<PieChartInput> {
    return listOf(
        PieChartInput(Color.Gray, 20.0, "Ropa"),
        PieChartInput(Color.White, 50.0, "Electrodomesticos"),
        PieChartInput(Color.Blue, 100.0, "Gastos Universitarios"),
        PieChartInput(Color.Green, 100.4, "Comida"),
        PieChartInput(Color.Magenta, 70.8, "Bebidas"),
        PieChartInput(Color.Red, 30.8, "Otros"),
    )
}

fun listOfPieChartInputOrder(): List<PieChartInput> {
    return listOfPieChartInput().sortedByDescending { it.value }
}
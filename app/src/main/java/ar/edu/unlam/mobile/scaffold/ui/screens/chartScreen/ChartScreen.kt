package ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.domain.models.PieChartInput
import ar.edu.unlam.mobile.scaffold.domain.models.Screens
import ar.edu.unlam.mobile.scaffold.ui.components.PieChart
import ar.edu.unlam.mobile.scaffold.ui.components.Statistics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChartScreen(
    controller: NavHostController,
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
    ) {
        CenterAlignedTopAppBar(
            title = { Text(text = "Tus gastos") },
        )
        Body(categoria = "Categoría", porcentaje = "Porcentaje", total = "Total", list = listOfPieChartInputOrder() as MutableList<PieChartInput>, Color.Blue, Color.LightGray)
        Spacer(modifier = Modifier.height(20.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(alignment = Alignment.BottomEnd),
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    controller.navigate(Screens.TransactionScreen.route)
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
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
                data = listOfPieChartInput(),
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

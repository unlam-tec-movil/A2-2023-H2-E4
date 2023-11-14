package ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Screens
import ar.edu.unlam.mobile.scaffold.ui.components.PieChart
import ar.edu.unlam.mobile.scaffold.ui.components.Statistics

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChartScreen(
    controller: NavHostController,
    viewModel: ChartScreenViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Tus gastos",
                    fontWeight = FontWeight.Bold,
                )
            },
        )
        Body(categoria = "Categoría", porcentaje = "Porcentaje", total = "Total", viewModel, Color.Blue, Color.LightGray, controller)
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun Body(
    categoria: String,
    porcentaje: String,
    total: String,
    viewModel: ChartScreenViewModel,
    color1: Color,
    color2: Color,
    controller: NavHostController,
) {
    val listPieChartInput by viewModel.pieCharInputList.collectAsState()
    Column {
        Column(
            modifier = Modifier
                .height(300.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
        ) {
            PieChart(
                data = listPieChartInput,
            )
        }
        Box(modifier = Modifier.fillMaxWidth()) {
            FloatingActionButton(
                modifier = Modifier
                    .padding(all = 5.dp)
                    .align(alignment = Alignment.CenterEnd),
                shape = MaterialTheme.shapes.medium,
                onClick = {
                    controller.navigate(Screens.AddTransactionScreen.route)
                },
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
            }
        }
        Statistics(categoria = categoria, porcentaje = porcentaje, total = total, list = listPieChartInput, color1, color2, viewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun ChartScreenPreview() {
}

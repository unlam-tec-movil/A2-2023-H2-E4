package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffold.data.transaction.models.PieChartInput
import ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen.ChartScreenViewModel

@Composable
fun Statistics(
    list: List<PieChartInput>,
    viewModel: ChartScreenViewModel,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 3.dp, vertical = 10.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Categoria", fontSize = 23.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            Text(text = "Porcentaje", fontSize = 23.sp, color = Color.Black, fontWeight = FontWeight.Bold)
            Text(text = "Total", fontSize = 23.sp, color = Color.Black, fontWeight = FontWeight.Bold)
        }
        ShowCategories(list, viewModel)
    }
}

@Composable
fun ShowCategories(data: List<PieChartInput>, viewModel: ChartScreenViewModel) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(color = Color.Gray)
    ) {
        items(data) { item ->
            ListItemRow(item, viewModel)
        }
    }
}

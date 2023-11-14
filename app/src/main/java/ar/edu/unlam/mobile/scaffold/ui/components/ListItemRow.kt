package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffold.data.transaction.models.PieChartInput
import ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen.ChartScreenViewModel

@Composable
fun ListItemRow(item: PieChartInput, viewModel: ChartScreenViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Spacer(modifier = Modifier.width(20.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .size(30.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(color = Color(android.graphics.Color.parseColor(item.category.color)))
                    .border(
                        width = 2.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(20.dp)
                    )
            )
            Text(item.category.name, textAlign = TextAlign.Left, fontSize = 15.sp)
        }

        Column(
            modifier = Modifier
                .width(100.dp)
                .fillMaxHeight()
        ) {
            Text(
                "${viewModel.calcularPorcentaje(item)}%",
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
        }

        Column(
            modifier = Modifier
                .width(100.dp)
                .fillMaxHeight()
        ) {
            Text("${item.totalAmount}", textAlign = TextAlign.Right, fontSize = 15.sp)
        }
    }
}

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
import ar.edu.unlam.mobile.scaffold.domain.models.PieChartInput
import ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen.listOfPieChartInput
import kotlin.math.roundToInt

@Composable
fun ListItemRow(item: PieChartInput) {
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
            Text(item.description, textAlign = TextAlign.Left, fontSize = 15.sp)
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
            Text("$${item.value}", textAlign = TextAlign.Right, fontSize = 15.sp)
        }
    }
}

fun porcentaje(item: PieChartInput): Int {
    var total = 0.0
    listOfPieChartInput().forEach {
        total += it.value
    }
    return ((item.value).div(total).times(100)).roundToInt()
}

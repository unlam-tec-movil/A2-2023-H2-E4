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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffold.data.category.repository.CategoryEntity
import ar.edu.unlam.mobile.scaffold.domain.models.ColorsCategory
import ar.edu.unlam.mobile.scaffold.ui.theme.Amarillo
import ar.edu.unlam.mobile.scaffold.ui.theme.Azul
import ar.edu.unlam.mobile.scaffold.ui.theme.Morado
import ar.edu.unlam.mobile.scaffold.ui.theme.Naranja
import ar.edu.unlam.mobile.scaffold.ui.theme.Rojo
import ar.edu.unlam.mobile.scaffold.ui.theme.Verde

@Composable
fun ListItemRow(item: CategoryEntity) {
    val color = item.color.toString()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = getColorBackground(color))
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
            //Text("${porcentaje(item)}%", textAlign = TextAlign.Center, fontSize = 15.sp)
            Text("0%", textAlign = TextAlign.Center, fontSize = 15.sp)
        }
        Column(
            Modifier
                .padding(5.dp)
                .width(100.dp)
                .fillMaxHeight()
        ) {
            Text("$0", textAlign = TextAlign.Right, fontSize = 15.sp)
        }
    }
}

fun getColorBackground(hexColor: String): Color {
    return when (hexColor) {
        ColorsCategory.ROJO.colorHex -> Rojo
        ColorsCategory.VERDE.colorHex -> Verde
        ColorsCategory.AZUL.colorHex -> Azul
        ColorsCategory.AMARILLO.colorHex -> Amarillo
        ColorsCategory.NARANJA.colorHex -> Naranja
        ColorsCategory.MORADO.colorHex -> Morado
        else -> Rojo // Color predeterminado para la categoria
    }
}

/*
fun porcentaje(item: PieChartInput): Int {
    var total = 0.0
    listOfPieChartInput().forEach {
        total += it.value
    }
    return ((item.value).div(total).times(100)).roundToInt()
}*/

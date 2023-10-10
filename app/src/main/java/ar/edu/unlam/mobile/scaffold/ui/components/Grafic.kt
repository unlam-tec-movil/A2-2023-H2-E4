package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffold.domain.kitty.models.PieChartInput
import androidx.compose.material3.Text as Text


@Composable
fun pieChart(
    data: List<PieChartInput>,
    radiousOuter: Float = 500f,
    innerRadious:Float = 250f,
    transparentwidth:Float = 70f
) {
    ///obtenemos el total de lo gastado
    val totalSum = data.sumOf{
        it.value
    }
    ////vamos a crear
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier
                .fillMaxSize()
                .pointerInput(true) {

                }
            ) {
                val width = size.width
                val height = size.height
                val circleCenter = Offset(x = width / 2f, y = height / 2f)
                val anglePerValue = 360f/totalSum

                var currentStarAngle = 0.0

                data.forEach { pieChartInput ->
                    val scale = if (pieChartInput.isTapped) 1.1f else 1.0f
                    val angleToDraw = pieChartInput.value * anglePerValue

                    scale(scale) {
                        drawArc(
                            color = pieChartInput.color,
                            startAngle = currentStarAngle.toFloat(),
                            sweepAngle = angleToDraw.toFloat(),
                            useCenter = true,
                            size = Size(
                                width = radiousOuter * 2f,
                                height = radiousOuter * 2f
                            ),
                            topLeft = Offset(
                                x = (width.minus(radiousOuter * 2f)).div(2f),
                                y = (height.minus(radiousOuter * 2f)).div(2f)
                            )
                        )
                        currentStarAngle += angleToDraw
                    }
                }
                drawContext.canvas.nativeCanvas.apply {
                    drawCircle(
                        circleCenter.x,
                        circleCenter.y,
                        innerRadious,
                       android.graphics.Paint().apply {
                           color = Color.White.copy(alpha = 0.6f).toArgb()
                           setShadowLayer(10f,0f,0f,Color.Gray.toArgb())
                       }
                    )
                }

                drawCircle(
                    color = Color.White.copy(0.2f),
                    radius = innerRadious * transparentwidth /2f
                )


            }
            TextCenter(centerText = "$$totalSum", innerRadius = innerRadious)

        }
    }

}

@Composable
fun TextCenter(centerText:String,innerRadius: Float){
    Column(
        modifier = Modifier
            .width(Dp(innerRadius / 1.5f)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Total",
            fontWeight = FontWeight.SemiBold,
            fontSize = 45.sp,

        )
        Text(
            text = centerText,
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp
        )
    }


}
@Preview(showBackground = true)
@Composable
fun pieChartPreview(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        pieChart(data = listOf(
            PieChartInput(Color.Black,20.0,"Ropa"),
            PieChartInput(Color.White,50.0,"electrodomesticos"),
            PieChartInput(Color.Blue,100.0,"gastosUniversitarios"),
            PieChartInput(Color.Green,100.4,"comida"),
            PieChartInput(Color.Magenta,70.8,"bebidas"),
            PieChartInput(Color.Red,30.8,"otros")
        ))
    }
}
package ar.edu.unlam.mobile.scaffold.ui.components
import androidx.compose.foundation.Canvas
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
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffold.data.transaction.models.PieChartInput
import androidx.compose.material3.Text as Text
@Composable
fun PieChart(
    data: List<PieChartInput>,
    outerRadius: Float = 500f,
    innerRadius: Float = 250f,
    transparentwidth: Float = 70f,
) {
    val totalSum = data.sumOf { it.totalAmount }
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            contentAlignment = Alignment.Center,
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(true) { },
            )
            {
                val width = size.width
                val height = size.height
                val circleCenter = Offset(x = width.div(2f), y = height.div(2f))
                var currentStarAngle = 0.0
                data.forEach { pieChartInput ->
                    val scale = if (pieChartInput.isTapped) 1.1f else 1.0f
                    val angleToDraw = pieChartInput.totalAmount.div(totalSum) * 360f
                    scale(scale) {
                        drawArc(
                            color = Color(android.graphics.Color.parseColor(pieChartInput.category.color)),
                            startAngle = currentStarAngle.toFloat(),
                            sweepAngle = angleToDraw.toFloat(),
                            useCenter = true,
                            size = Size(
                                width = outerRadius.times(2f),
                                height = outerRadius.times(2f),
                            ),
                            topLeft = Offset(
                                x = (width.minus(outerRadius.times(2f))).div(2f),
                                y = (height.minus(outerRadius.times(2f))).div(2f),
                            ),
                        )
                        currentStarAngle += angleToDraw
                    }
                }
                val paint = android.graphics.Paint().also {
                    it.color = Color.White.copy(alpha = 0.6f).toArgb()
                    it.setShadowLayer(10f, 0f, 0f, Color.Gray.toArgb())
                }
                drawContext.canvas.nativeCanvas.apply {
                    drawCircle(
                        circleCenter.x,
                        circleCenter.y,
                        innerRadius,
                        paint,
                    )
                }

                drawCircle(
                    color = Color.White.copy(0.2f),
                    radius = innerRadius.times(transparentwidth).div(2f),
                )
            }
            TextCenter(text = "$$totalSum", innerRadius = innerRadius)
        }
    }
}

@Composable
fun TextCenter(
    text: String,
    innerRadius: Float,
) {
    Column(
        modifier = Modifier
            .width(Dp(innerRadius / 1.5f)),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Total",
            fontWeight = FontWeight.SemiBold,
            fontSize = 45.sp,
        )
        Text(
            text = text,
            fontWeight = FontWeight.SemiBold,
            fontSize = 25.sp,
        )
    }
}

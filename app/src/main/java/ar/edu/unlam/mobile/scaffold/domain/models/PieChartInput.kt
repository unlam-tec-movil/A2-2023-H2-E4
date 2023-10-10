package ar.edu.unlam.mobile.scaffold.domain.models

data class PieChartInput(
    val color: androidx.compose.ui.graphics.Color,
    val value: Double,
    val description:String,///esto podria ser la categoria
    val isTapped: Boolean = false
)

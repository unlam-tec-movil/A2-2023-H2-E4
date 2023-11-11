package ar.edu.unlam.mobile.scaffold.data.transaction.models

data class PieChartInput(
    val category:Category,
    val totalAmount:Double,
    val isTapped: Boolean = false,
)

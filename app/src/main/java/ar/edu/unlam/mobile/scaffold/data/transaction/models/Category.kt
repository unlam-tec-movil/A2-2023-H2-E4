package ar.edu.unlam.mobile.scaffold.data.transaction.models

import androidx.compose.ui.graphics.Color

data class Category(
    var id: Int = 0,
    var type: TransactionType = TransactionType.EXPENSE,
    var name: String = "",
    var totalAmount: Double = 0.0,
    var color: Color = Color.LightGray,
)

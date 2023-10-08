package ar.edu.unlam.mobile.scaffold.data.category

import androidx.compose.ui.graphics.Color
import ar.edu.unlam.mobile.scaffold.data.TransactionType

data class Category (
    var id: Int = 0,
    var type: TransactionType = TransactionType.Expense,
    var name: String = "",
    var totalAmount: Double = 0.0,
    var color: Color = Color.LightGray
)

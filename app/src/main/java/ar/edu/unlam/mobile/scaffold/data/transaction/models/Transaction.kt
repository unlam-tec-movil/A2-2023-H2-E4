package ar.edu.unlam.mobile.scaffold.data.transaction.models

import java.time.LocalDate

data class Transaction(
    val id: Int,
    val type: TransactionType,
    val amount: Float,
    val currency: Currency,
    val category: Category,
    val description: String,
    val date: LocalDate,
)

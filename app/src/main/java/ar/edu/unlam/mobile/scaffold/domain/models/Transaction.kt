package ar.edu.unlam.mobile.scaffold.domain.models

import java.time.LocalDate

data class Transaction(
    val type: TransactionType,
    val amount: Float,
//    val currency: Currency, ToDo: Implementar Currency
    val category: Category,
    val description: String,
    val date: LocalDate,
)

package ar.edu.unlam.mobile.scaffold.domain.models

import java.time.LocalDate

data class Transaction(
    val type: TransactionType,
    val amount: Float,
    val category: Category,
    val date: LocalDate,
)
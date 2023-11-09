package ar.edu.unlam.mobile.scaffold.data.transaction.models

data class Transaction(
    val id: Int,
    val type: TransactionType,
    val amount: Double,
    val currency: Currency,
    val category: Category,
    val description: String,
    val date: String,
)

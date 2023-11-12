package ar.edu.unlam.mobile.scaffold.data.transaction.models

data class Transaction(
    val id: Int,
    val type: TransactionType,
    val category: Category,
    val currency: Currency,
    val amount: Double,
    val date: String,
    val description: String,
) {
    fun toDomain(): Transaction {
        return this
    }
}

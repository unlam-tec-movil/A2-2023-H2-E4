package ar.edu.unlam.mobile.scaffold.data.transaction.models

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity

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

    fun toEntity(): TransactionEntity {
        return TransactionEntity(
            id = id,
            transactionType = type,
            categoryId = category.id,
            currencyId = currency.id,
            amount = amount,
            date = date,
            description = description,
        )
    }
}

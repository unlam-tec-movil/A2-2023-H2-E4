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

// {
//    constructor(entity: TransactionEntity, category: CategoryEntity, currency: CurrencyEntity) : this(
//        id = entity.id,
//        description = entity.description,
//        transactionType = TransactionType.fromInt(entity.transaction_type_id),
//        category = Category(entity = category),
//        currency = currency,
//        date = entity.date,
//        amount = entity.amount
//    )
// }

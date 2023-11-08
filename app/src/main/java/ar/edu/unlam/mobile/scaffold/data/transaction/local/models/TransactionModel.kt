package ar.edu.unlam.mobile.scaffold.data.transaction.local.models

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CurrencyEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionTypeEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.toDomain
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import java.time.LocalDate

data class TransactionModel(
    val id: Int,
    val type: String,
    val currencyEntity: CurrencyEntity,
    val transactionCategoryEntity: CategoryEntity,
    val amount: Double,
    val date: LocalDate,
    val description: String,
)

fun TransactionModel.toDomain() = Transaction(
    id = id,
    type = TransactionTypeEntity(id = 0, description = type).toDomain(),
    currency = currencyEntity.toDomain(),
    amount = amount.toFloat(),
    date = date,
    description = description,
    category = transactionCategoryEntity.toDomain(),
)

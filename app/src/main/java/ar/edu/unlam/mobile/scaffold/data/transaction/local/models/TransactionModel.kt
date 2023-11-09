package ar.edu.unlam.mobile.scaffold.data.transaction.local.models

import androidx.room.Embedded
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CurrencyEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionTypeEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.toDomain
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction

data class TransactionModel(
    val id: Int,
    @Embedded
    val typeEntity: TransactionTypeEntity,
    @Embedded
    val currencyEntity: CurrencyEntity,
    @Embedded
    val CategoryEntity: CategoryEntity,
    val amount: Double,
    val date: String,
    val description: String,
)
fun TransactionModel.toDomain() = Transaction(
    id = id,
    type = typeEntity.toDomain(),
    currency = currencyEntity.toDomain(),
    category = CategoryEntity.toDomain(),
    amount = amount,
    date = date,
    description = description,
)

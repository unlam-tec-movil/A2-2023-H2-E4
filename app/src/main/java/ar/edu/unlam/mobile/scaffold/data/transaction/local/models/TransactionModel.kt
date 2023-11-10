package ar.edu.unlam.mobile.scaffold.data.transaction.local.models

import androidx.room.Embedded
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CurrencyEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionTypeEntity

data class TransactionModel(
    val id: Int,
    @Embedded
    val typeEntity: TransactionTypeEntity,
    @Embedded
    val CategoryEntity: CategoryEntity,
    @Embedded
    val currencyEntity: CurrencyEntity,
    val amount: Double,
    val date: String,
    val description: String,
)

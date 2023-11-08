package ar.edu.unlam.mobile.scaffold.data.transaction.local.entities

import androidx.room.Embedded
import androidx.room.Relation

// Definición de relaciones
data class TransactionWithDetails(
    @Embedded
    val transaction: TransactionEntity,
    @Relation(
        parentColumn = "transaction_type_id",
        entityColumn = "id",
    )
    val transactionType: TransactionTypeEntity,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id",
    )
    val category: CategoryEntity,
    @Relation(
        parentColumn = "currency_id",
        entityColumn = "id",
    )
    val currency: CurrencyEntity,
)

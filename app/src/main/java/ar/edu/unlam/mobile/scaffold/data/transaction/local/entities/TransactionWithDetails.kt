package ar.edu.unlam.mobile.scaffold.data.transaction.local.entities

import androidx.room.Embedded
import androidx.room.Relation

// Definición de relaciones
data class TransactionWithDetails(
    @Embedded
    val transaction: TransactionEntity,
    @Relation(
        entity = TransactionTypeEntity::class,
        parentColumn = "transaction_type_id",
        entityColumn = "id",
    )
    val transactionType: TransactionTypeEntity,
    @Relation(
        entity = CategoryEntity::class,
        parentColumn = "category_id",
        entityColumn = "id",
    )
    val category: CategoryEntity,
    @Relation(
        entity = CurrencyEntity::class,
        parentColumn = "currency_id",
        entityColumn = "id",
    )
    val currency: CurrencyEntity,
)

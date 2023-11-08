package ar.edu.unlam.mobile.scaffold.data.transaction.local.entities

import androidx.room.Embedded
import androidx.room.Relation

// Definición de relaciones
data class CategoryWithDetails(
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "transaction_type_id",
        entityColumn = "id"
    )
    val transactionType: TransactionTypeEntity,
    @Relation(
        parentColumn = "color_id",
        entityColumn = "id"
    )
    val color: ColorEntity,
)

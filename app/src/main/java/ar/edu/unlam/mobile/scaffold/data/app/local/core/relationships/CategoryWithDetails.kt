package ar.edu.unlam.mobile.scaffold.data.app.local.core.relationships

import androidx.room.Embedded
import androidx.room.Relation
import ar.edu.unlam.mobile.scaffold.data.app.local.core.category.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.app.local.core.color.ColorEntity
import ar.edu.unlam.mobile.scaffold.data.app.local.core.transactionType.TransactionTypeEntity

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

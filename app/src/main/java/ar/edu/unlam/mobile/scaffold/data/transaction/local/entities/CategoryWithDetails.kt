package ar.edu.unlam.mobile.scaffold.data.transaction.local.entities

import androidx.room.Embedded
import androidx.room.Relation
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category

// Definición de relaciones
data class CategoryWithDetails(
    @Embedded val category: CategoryEntity,
    @Relation(
        parentColumn = "transaction_type_id", // Actualiza el nombre de la columna
        entityColumn = "id",
    )
    val transactionType: TransactionTypeEntity,
) {
    fun toDomain(): Category {
        return Category(
            id = category.id,
            type = transactionType.toDomain(),
            name = category.name,
            color = category.colorString,
        )
    }
}

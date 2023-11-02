package ar.edu.unlam.mobile.scaffold.data.app.local.core.relationships

import androidx.room.Embedded
import androidx.room.Relation
import ar.edu.unlam.mobile.scaffold.data.app.local.core.category.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.app.local.core.currency.CurrencyEntity
import ar.edu.unlam.mobile.scaffold.data.app.local.core.transaction.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.app.local.core.transactionType.TransactionTypeEntity

// Definición de relaciones
data class TransactionWithDetails(
    @Embedded
    val transaction: TransactionEntity,
    @Relation(
        parentColumn = "transaction_type_id",
        entityColumn = "id"
    )
    val transactionType: TransactionTypeEntity,
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id"
    )
    val category: CategoryEntity,
    @Relation(
        parentColumn = "currency_id",
        entityColumn = "id"
    )
    val currency: CurrencyEntity
)
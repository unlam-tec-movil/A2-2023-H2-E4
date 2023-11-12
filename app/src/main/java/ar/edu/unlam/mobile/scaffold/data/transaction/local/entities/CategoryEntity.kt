package ar.edu.unlam.mobile.scaffold.data.transaction.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType

@Entity(tableName = "Category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "transaction_type")
    val categoryTransactionType: TransactionType, // Cambiado el nombre de la columna
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "colorString")
    val colorString: String,
) {
    fun toDomain(): Category {
        return Category(
            id = id,
            type = categoryTransactionType,
            name = name,
            color = colorString,
        )
    }
}

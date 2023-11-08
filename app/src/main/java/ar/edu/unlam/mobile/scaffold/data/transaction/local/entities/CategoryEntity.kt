package ar.edu.unlam.mobile.scaffold.data.transaction.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category

@Entity(tableName = "Category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "transaction_type_id")
    val transactionTypeId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "color_id")
    val colorId: Int,
)

fun CategoryEntity.toDomain(): Category = Category(
    id = id,
    transactionTypeId = transactionTypeId,
    name = name,
    colorId = colorId,
)

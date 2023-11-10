package ar.edu.unlam.mobile.scaffold.data.transaction.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionTypeEnum

@Entity(tableName = "Category")
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    //TransactionType en la bd se tiene que guardar como Int
    @ColumnInfo(name = "transaction_type_id")
    val categoryTransactionTypeId: Int,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "colorString")
    val colorString: String,
)

fun CategoryEntity.toDomain(): Category {
    // Convierte el Int almacenado en categoryTransactionTypeId a un objeto TransactionType
    val transactionType = TransactionTypeEnum.fromInt(categoryTransactionTypeId)
    return Category(
        id = id,
        //aca se lo tengo que pasar como objeto no como Int
        type = transactionType,
        name = name,
        color = colorString
    )
}
package ar.edu.unlam.mobile.scaffold.data.transaction.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType

@Entity(tableName = "TransactionType")
data class TransactionTypeEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "description")
    val description: String,
)

fun TransactionTypeEntity.toDomain(): TransactionType = TransactionType(
    id = id,
    description = description,
)

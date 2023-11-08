package ar.edu.unlam.mobile.scaffold.data.transaction.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Currency

@Entity(tableName = "Currency")
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "code")
    val code: String,
    @ColumnInfo(name = "description")
    val description: String,
)

fun CurrencyEntity.toDomain(): Currency = Currency(
    id = id,
    code = code,
    description = description,
)

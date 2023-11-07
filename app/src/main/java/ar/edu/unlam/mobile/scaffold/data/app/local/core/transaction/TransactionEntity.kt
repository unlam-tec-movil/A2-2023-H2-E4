package ar.edu.unlam.mobile.scaffold.data.app.local.core.transaction

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "Transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "transaction_type_id")
    val transaction_type_id: Int,
    @ColumnInfo(name = "category_id")
    val category_id: Int,
    @ColumnInfo(name = "currency_id")
    val currency_id: Int,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "amount")
    val amount: Float,
)

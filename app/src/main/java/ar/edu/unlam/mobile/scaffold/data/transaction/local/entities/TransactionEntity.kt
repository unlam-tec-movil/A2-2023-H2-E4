package ar.edu.unlam.mobile.scaffold.data.transaction.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Transaction")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "transaction_type_id")
    val transactionTypeID: Int,
    @ColumnInfo(name = "category_id")
    val categoryId: Int,
    @ColumnInfo(name = "currency_id")
    val currencyId: Int,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "amount")
    val amount: Float,
)

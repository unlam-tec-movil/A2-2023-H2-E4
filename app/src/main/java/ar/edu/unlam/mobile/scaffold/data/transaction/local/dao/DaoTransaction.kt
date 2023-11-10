package ar.edu.unlam.mobile.scaffold.data.transaction.local.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionWithDetails
import kotlinx.coroutines.flow.Flow
@Dao
interface DaoTransaction {
    @Query("SELECT * FROM `Transaction`")
    fun getAllTransactions(): List<TransactionEntity>

    @Insert
    fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM `Transaction` WHERE id = :id")
    fun getTransactionById(id: Int): TransactionEntity?

    @Query("DELETE FROM `Transaction` WHERE id = :id")
    fun deleteTransactionById(id: Int)

    @Update
    fun updateTransaction(transaction: TransactionEntity)

    @Transaction
    @Query(
        /* value = */
        "SELECT t.id as id, " +
            "tt.id as transaction_type_id, " +
            "c.id as category_id, " +
            "cu.id as currency_id, " +
            "t.amount as amount, " +
            "t.date as date, " +
            "t.description as description " +
            "FROM `Transaction` t " +
            "INNER JOIN `Category` c ON t.category_id = c.id " +
            "INNER JOIN `Currency` cu ON t.currency_id = cu.id " +
            "INNER JOIN `TransactionType` tt ON t.transaction_type_id = tt.id",
    )
    fun getTransaction(): Flow<List<TransactionWithDetails>>
}

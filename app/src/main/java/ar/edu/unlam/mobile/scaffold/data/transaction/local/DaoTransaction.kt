package ar.edu.unlam.mobile.scaffold.data.transaction.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.models.TransactionModel

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

    @Query(
        "SELECT tt.description as type FROM `Transaction` t " +
            "JOIN `Category` c ON t.category_id = c.id" +
            " JOIN `Currency` cu  ON t.category_id = cu.id" +
            " JOIN `TransactionType` tt ON t.transaction_type_id = tt.id",
    )
    fun getTransactions(): List<TransactionModel>
}

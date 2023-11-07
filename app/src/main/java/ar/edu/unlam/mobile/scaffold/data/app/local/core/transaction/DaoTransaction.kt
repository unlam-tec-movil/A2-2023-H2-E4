package ar.edu.unlam.mobile.scaffold.data.app.local.core.transaction

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

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
}

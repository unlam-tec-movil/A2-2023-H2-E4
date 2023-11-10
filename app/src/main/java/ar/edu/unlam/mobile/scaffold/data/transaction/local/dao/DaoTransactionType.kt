package ar.edu.unlam.mobile.scaffold.data.transaction.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionTypeEntity

@Dao
interface DaoTransactionType {
    @Insert
    fun insertTransactionType(transactionType: TransactionTypeEntity)

    @Update
    fun updateTransactionType(transactionType: TransactionTypeEntity)

    @Delete
    fun deleteTransactionType(transactionType: TransactionTypeEntity)

    @Query("SELECT * FROM TransactionType")
    fun getAllTransactionTypes(): List<TransactionTypeEntity>

    @Query("SELECT * FROM TransactionType WHERE id = :transactionTypeId")
    fun getTransactionTypeById(transactionTypeId: Int): TransactionTypeEntity
}

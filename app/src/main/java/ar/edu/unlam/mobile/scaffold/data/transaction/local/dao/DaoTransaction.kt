package ar.edu.unlam.mobile.scaffold.data.transaction.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionWithDetails
import kotlinx.coroutines.flow.Flow

// https://developer.android.com/training/data-storage/room/async-queries#one-shot
@Dao
interface DaoTransaction {
    @Query("SELECT * FROM `Transaction`")
    suspend fun getAllTransactions(): List<TransactionEntity>

    @Insert
    suspend fun insertTransaction(transaction: TransactionEntity)

//    @Query("SELECT * FROM `Transaction` WHERE id = :id")
//    suspend fun getTransactionById(id: Int): TransactionEntity?

    @Query("DELETE FROM `Transaction` WHERE id = :id")
    suspend fun deleteTransactionById(id: Int)

    @Update
    suspend fun updateTransaction(transaction: TransactionEntity)

    @Transaction
    @Query(
        "SELECT t.id as id, t.transaction_type, c.id as category_id, cu.id as currency_id, t.amount as amount, t.date as date, t.description as description FROM `Transaction` t INNER JOIN `Category` c ON t.category_id = c.id INNER JOIN `Currency` cu ON t.currency_id = cu.id",
    )
    suspend fun getTransaction(): List<TransactionWithDetails>

    @Transaction
    @Query(
        "SELECT t.transaction_type, t.id as id, c.id as category_id, cu.id as currency_id, t.amount as amount, t.date as date, t.description as description FROM `Transaction` t INNER JOIN `Category` c ON t.category_id = c.id INNER JOIN `Currency` cu ON t.currency_id = cu.id " +
            "WHERE strftime('%Y', t.date) = :year",
    )
    fun getTransactionForYear(year: String): Flow<List<TransactionWithDetails>>

    @Transaction
    @Query(
        "SELECT t.transaction_type," +
            " t.id as id, " +
            "c.id as category_id, " +
            "cu.id as currency_id, " +
            "t.amount as amount, " +
            "t.date as date, " +
            "t.description as description " +
            "FROM `Transaction` t INNER JOIN `Category` c ON t.category_id = c.id INNER JOIN `Currency` cu ON t.currency_id = cu.id " +
            "WHERE strftime('%m', t.date) = :mount",
    )
    fun getTransactionForMonth(mount: String): Flow<List<TransactionWithDetails>>

    @Transaction
    @Query(
        "SELECT t.transaction_type, t.id as id, c.id as category_id, cu.id as currency_id, t.amount as amount, t.date as date, t.description as description FROM `Transaction` t INNER JOIN `Category` c ON t.category_id = c.id INNER JOIN `Currency` cu ON t.currency_id = cu.id WHERE strftime('%m', t.date) = :month AND strftime('%Y', t.date) = :year",
    )
    fun getTransactionForMonthAndYear(month: String, year: String): Flow<List<TransactionWithDetails>>

    @Transaction
    @Query(
        "SELECT t.id as id, t.transaction_type, c.id as category_id, cu.id as currency_id, t.amount as amount, t.date as date, t.description as description FROM `Transaction` t INNER JOIN `Category` c ON t.category_id = c.id INNER JOIN `Currency` cu ON t.currency_id = cu.id WHERE t.id = :transactionId",
    )
    suspend fun getTransactionById(transactionId: Int): TransactionWithDetails
}

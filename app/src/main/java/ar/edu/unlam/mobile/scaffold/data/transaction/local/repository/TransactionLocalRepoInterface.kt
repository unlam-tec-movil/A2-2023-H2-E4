package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionWithDetails
import kotlinx.coroutines.flow.Flow

interface TransactionLocalRepoInterface {
    fun getTransactionForYear(year: String): Flow<List<TransactionWithDetails>>

    fun getTransactionForMonth(month: String): Flow<List<TransactionWithDetails>>

    fun getTransactionForMonthAndYear(month: String, year: String): Flow<List<TransactionWithDetails>>
    suspend fun getAllTransactions(): Flow<List<TransactionWithDetails>>

    suspend fun addTransaction(transaction: TransactionEntity)

    suspend fun getTransactionById(id: Int): Flow<TransactionWithDetails>
}

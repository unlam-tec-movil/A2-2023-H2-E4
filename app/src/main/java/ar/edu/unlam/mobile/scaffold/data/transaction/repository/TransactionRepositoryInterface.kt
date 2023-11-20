package ar.edu.unlam.mobile.scaffold.data.transaction.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.domain.provider.DispatcherProvider
import kotlinx.coroutines.flow.Flow

interface TransactionRepositoryInterface {
    suspend fun addTransaction(transaction: TransactionEntity)
    suspend fun getAllTransactions(dispatcherProvider: DispatcherProvider): Flow<List<Transaction>>

    suspend fun getTransactionForYear(year: String): Flow<List<Transaction>>

    suspend fun getTransactionForMonth(month: String): Flow<List<Transaction>>

    suspend fun getTransactionForMonthAndYear(month: String, year: String): Flow<List<Transaction>>
}

package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionWithDetails
import kotlinx.coroutines.flow.Flow

interface TransactionLocalRepoInterface {
    suspend fun getAllTransactions(): Flow<List<TransactionWithDetails>>
    suspend fun addTransaction(transaction: TransactionEntity)
}

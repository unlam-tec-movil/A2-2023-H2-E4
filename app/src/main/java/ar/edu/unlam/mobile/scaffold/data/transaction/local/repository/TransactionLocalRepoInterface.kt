package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionWithDetails
import kotlinx.coroutines.flow.Flow

interface TransactionLocalRepoInterface {
    fun getAllTransactions(): Flow<List<TransactionWithDetails>>
    fun addTransaction(transaction: TransactionEntity)
}

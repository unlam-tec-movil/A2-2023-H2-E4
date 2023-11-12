package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionServiceInterface {
    suspend fun insertTransaction(transaction: Transaction)

    suspend fun getAllTransactions(): Flow<List<Transaction>>

}

package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.models.TransactionModel
import kotlinx.coroutines.flow.Flow

interface TransactionLocalRepoInterface {
    fun getAllTransactions(): Flow<List<TransactionModel>>
    fun addTransaction(transaction: TransactionEntity)
}

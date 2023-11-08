package ar.edu.unlam.mobile.scaffold.data.transaction.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction

interface TransactionRepositoryInterface {
    suspend fun addTransaction(transaction: TransactionEntity)
    suspend fun getAllTransactions(): List<Transaction>
}

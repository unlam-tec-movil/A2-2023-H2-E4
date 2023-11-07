package ar.edu.unlam.mobile.scaffold.data.app.local.core.transaction

import ar.edu.unlam.mobile.scaffold.domain.models.Transaction

interface TransactionRepositoryInterface {
    suspend fun addTransaction(transaction: TransactionEntity)
    suspend fun getAllTransactions(): List<Transaction>
}

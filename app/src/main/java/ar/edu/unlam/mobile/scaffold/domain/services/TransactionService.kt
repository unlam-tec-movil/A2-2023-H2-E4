package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.data.transaction.repository.TransactionRepositoryInterface
import kotlinx.coroutines.flow.Flow

class TransactionService(
    private val repository: TransactionRepositoryInterface,
) : TransactionServiceInterface {
    override suspend fun insertTransaction(transaction: Transaction) {
        repository.addTransaction(transaction)
    }

    override suspend fun getAllTransactions(): Flow<List<Transaction>> {
        return repository.getAllTransactions()
    }
}

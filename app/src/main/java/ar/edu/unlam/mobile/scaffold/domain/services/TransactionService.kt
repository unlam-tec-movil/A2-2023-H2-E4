package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.data.transaction.repository.TransactionRepositoryInterface
import ar.edu.unlam.mobile.scaffold.domain.provider.DispatcherProvider
import kotlinx.coroutines.flow.Flow

class TransactionService(
    private val repository: TransactionRepositoryInterface,
    private val dispatcherProvider: DispatcherProvider,
) : TransactionServiceInterface {
    override suspend fun insertTransaction(transaction: Transaction) {
        repository.addTransaction(transaction.toEntity())
    }

    override suspend fun getAllTransactions(): Flow<List<Transaction>> {
        return repository.getAllTransactions(dispatcherProvider)
    }

    override suspend fun getTransactionForYear(year: String): Flow<List<Transaction>> {
        return repository.getTransactionForYear(year)
    }

    override suspend fun getTransactionForMonth(month: String): Flow<List<Transaction>> {
        return repository.getTransactionForMonth(month)
    }

    override suspend fun getTransactionForMonthAndYear(
        month: String,
        year: String,
    ): Flow<List<Transaction>> {
        return repository.getTransactionForMonthAndYear(month, year)
    }
}

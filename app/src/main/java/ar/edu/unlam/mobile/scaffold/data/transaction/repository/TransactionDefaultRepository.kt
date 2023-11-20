package ar.edu.unlam.mobile.scaffold.data.transaction.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.TransactionLocalRepoInterface
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.domain.provider.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionDefaultRepository @Inject constructor(
    private val transactionLocalRepo: TransactionLocalRepoInterface,
) : TransactionRepositoryInterface {
    override suspend fun addTransaction(transaction: TransactionEntity) {
        transactionLocalRepo.addTransaction(transaction)
    }

    override suspend fun getAllTransactions(dispatcherProvider: DispatcherProvider): Flow<List<Transaction>> {
        return transactionLocalRepo.getAllTransactions().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override suspend fun getTransactionForYear(year: String): Flow<List<Transaction>> {
        return transactionLocalRepo.getTransactionForYear(year).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override suspend fun getTransactionForMonth(month: String): Flow<List<Transaction>> {
        return transactionLocalRepo.getTransactionForMonth(month).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }

    override suspend fun getTransactionForMonthAndYear(
        month: String,
        year: String,
    ): Flow<List<Transaction>> {
        return transactionLocalRepo.getTransactionForMonthAndYear(month, year).map { list ->
            list.map {
                it.toDomain()
            }
        }
    }
}

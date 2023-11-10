package ar.edu.unlam.mobile.scaffold.data.transaction.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.TransactionLocalRepoInterface
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionDefaultRepository @Inject constructor(
    private val transactionLocalRepo: TransactionLocalRepoInterface
) : TransactionRepositoryInterface {
    override suspend fun addTransaction(transaction: TransactionEntity) {
        transactionLocalRepo.addTransaction(transaction)
    }

    override suspend fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionLocalRepo.getAllTransactions().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }
}




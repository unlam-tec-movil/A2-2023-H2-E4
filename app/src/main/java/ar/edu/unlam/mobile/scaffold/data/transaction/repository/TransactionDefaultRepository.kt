package ar.edu.unlam.mobile.scaffold.data.transaction.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.models.toDomain
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.TransactionLocalRepo
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlinx.coroutines.flow.flow

class TransactionDefaultRepository @Inject constructor(
    private val transactionLocalRepo: TransactionLocalRepo,
) : TransactionRepository {
    override suspend fun addTransaction(transaction: TransactionEntity) {
        daoTransaction.insertTransaction(transaction)
    }

    override suspend fun getAllTransactions(): Flow<List<Transaction>> {
        return flow {
            transactionLocalRepo.getAllTransactions().map { list ->
                list.map {
                    it.toDomain()
                }
            }
        }
    }
}

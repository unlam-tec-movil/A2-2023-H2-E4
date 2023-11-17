package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoTransaction
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionWithDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionRoomRepository @Inject constructor(
    private val daoTransaction: DaoTransaction,
) : TransactionLocalRepoInterface {
    override suspend fun getAllTransactions(): Flow<List<TransactionWithDetails>> {
        return flow {
            emit(daoTransaction.getTransaction())
        }
    }

    override suspend fun addTransaction(transaction: TransactionEntity) {
        daoTransaction.insertTransaction(transaction)
    }
}

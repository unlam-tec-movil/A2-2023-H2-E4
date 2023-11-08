package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoTransaction
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.models.TransactionModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionRoomRepository @Inject constructor(
    private val daoTransaction: DaoTransaction,
) : TransactionLocalRepoInterface {
    override fun getAllTransactions(): Flow<List<TransactionModel>> {
        return flow {
            emit(daoTransaction.getTransactions())
        }
    }

    override fun addTransaction(transaction: TransactionEntity) {
        daoTransaction.insertTransaction(transaction)
    }
}

package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.DaoTransaction
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TransactionRoomRepository @Inject constructor(
    private val daoTransaction: DaoTransaction,
) : TransactionLocalRepo {
    override fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return flow {
            emit(daoTransaction.getAllTransactions())
        }
    }
}

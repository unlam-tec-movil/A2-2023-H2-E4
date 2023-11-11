package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoTransaction
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionWithDetails
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionServiceInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TransactionRoomRepository @Inject constructor(
    private val daoTransaction: DaoTransaction,
) : TransactionLocalRepoInterface,TransactionServiceInterface{
    override fun getAllTransactions(): Flow<List<TransactionWithDetails>> {
        return daoTransaction.getTransaction()
    }

    override fun addTransaction(transaction: TransactionEntity) {
        daoTransaction.insertTransaction(transaction)
    }

    override suspend fun insertTransaction() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllTransaction(): List<Transaction> {
        val transactionWithDetailsList = daoTransaction.getTransaction().first() // Recolecta el primer valor del flujo
        return transactionWithDetailsList.map {
            it.toDomain()
        }
       /* return daoTransaction.getTransaction().first().map {
            value: List<TransactionWithDetails> ->
            value.map {
                it.toDomain()
            }
        }*/
    }


}

package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoTransaction
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionWithDetails
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRoomRepository @Inject constructor(
    private val daoTransaction: DaoTransaction,
) : TransactionLocalRepoInterface {
    override fun getAllTransactions(): Flow<List<TransactionWithDetails>> {
        return daoTransaction.getTransaction()
    }

    override fun addTransaction(transaction: TransactionEntity) {
        daoTransaction.insertTransaction(transaction)
    }

    override fun getTransactionForYear(year: String): Flow<List<TransactionWithDetails>> {
        return daoTransaction.getTransactionForYear(year)
    }

    override fun getTransactionForMonth(month: String): Flow<List<TransactionWithDetails>> {
        return daoTransaction.getTransactionForMonth(month)
    }

    override fun getTransactionForMonthAndYear(
        month: String,
        year: String,
    ): Flow<List<TransactionWithDetails>> {
        return daoTransaction.getTransactionForMonthAndYear(month, year)
    }
}

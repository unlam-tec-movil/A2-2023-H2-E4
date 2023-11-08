package ar.edu.unlam.mobile.scaffold.data.transaction.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.DaoTransaction
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction

class TransactionRepository(private val daoTransaction: DaoTransaction) : TransactionRepositoryInterface {
    override suspend fun addTransaction(transaction: TransactionEntity) {
        daoTransaction.insertTransaction(transaction)
    }

    override suspend fun getAllTransactions(): List<Transaction> {
        return daoTransaction.getAllTransactions() //Aca transformar entity to model
    }
}

package ar.edu.unlam.mobile.scaffold.data.app.local.core.transaction

import ar.edu.unlam.mobile.scaffold.domain.models.Transaction

class TransactionRepository(private val daoTransaction: DaoTransaction) : TransactionRepositoryInterface {
    override suspend fun addTransaction(transaction: TransactionEntity) {
        daoTransaction.insertTransaction(transaction)
    }

    override suspend fun getAllTransactions(): List<Transaction> {
        return daoTransaction.getAllTransactions() //Aca transformar entity to model
    }
}

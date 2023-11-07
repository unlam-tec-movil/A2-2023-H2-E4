package ar.edu.unlam.mobile.scaffold.data.app.local.core.transaction

class TransactionRepository(private val daoTransaction: DaoTransaction) : TransactionRepositoryInterface {
    override suspend fun addTransaction(transaction: TransactionEntity) {
        daoTransaction.insertTransaction(transaction)
    }

    override fun getAllTransactions(): List<TransactionEntity> {
        return daoTransaction.getAllTransactions()
    }
}

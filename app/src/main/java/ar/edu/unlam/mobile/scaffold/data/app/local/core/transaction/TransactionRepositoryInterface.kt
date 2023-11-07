package ar.edu.unlam.mobile.scaffold.data.app.local.core.transaction

interface TransactionRepositoryInterface {
    suspend fun addTransaction(transaction: TransactionEntity)
    fun getAllTransactions(): List<TransactionEntity>
}

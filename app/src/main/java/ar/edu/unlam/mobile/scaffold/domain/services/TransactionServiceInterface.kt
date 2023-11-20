package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Currency
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType
import kotlinx.coroutines.flow.Flow

interface TransactionServiceInterface {
    suspend fun insertTransaction(
        type: TransactionType,
        category: Category?,
        currency: Currency?,
        amount: Double,
        comment: String,
    )
    suspend fun getAllTransactions(): Flow<List<Transaction>>
}

package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Currency
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType
import ar.edu.unlam.mobile.scaffold.data.transaction.repository.TransactionRepositoryInterface
import ar.edu.unlam.mobile.scaffold.domain.provider.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TransactionService(
    private val repository: TransactionRepositoryInterface,
    private val dispatcherProvider: DispatcherProvider,
) : TransactionServiceInterface {
    override suspend fun insertTransaction(
        type: TransactionType,
        category: Category?,
        currency: Currency?,
        amount: Double,
        comment: String,
    ) {
        createTransaction(type, category, currency, amount, comment)?.let {
            repository.addTransaction(it.toEntity())
        } ?: throw Exception("Transaction could not be created")
    }

    override suspend fun getAllTransactions(): Flow<List<Transaction>> {
        return repository.getAllTransactions(dispatcherProvider)
    }

    private fun createTransaction(
        type: TransactionType,
        category: Category?,
        currency: Currency?,
        amount: Double,
        comment: String,
    ): Transaction? {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val current = LocalDateTime.now().format(formatter).toString()

        return if (category != null && currency != null) {
            Transaction(
                id = 0,
                type = type,
                category = category,
                currency = currency,
                amount = amount,
                date = current,
                description = comment,
            )
        } else {
            null
        }
    }
}

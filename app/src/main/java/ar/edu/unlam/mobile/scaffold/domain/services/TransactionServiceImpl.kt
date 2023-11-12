package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.data.transaction.repository.TransactionRepositoryInterface
import ar.edu.unlam.mobile.scaffold.domain.provider.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionServiceImpl @Inject constructor(
    private val transactionRepository: TransactionRepositoryInterface,
    private val dispacherProvider: DispatcherProvider,
) : TransactionServiceInterface {
    override suspend fun insertTransaction() {
        TODO("Not yet implemented")
    }

    override suspend fun getAllTransaction(): Flow<List<Transaction>> = transactionRepository.getAllTransactions(dispacherProvider)
}

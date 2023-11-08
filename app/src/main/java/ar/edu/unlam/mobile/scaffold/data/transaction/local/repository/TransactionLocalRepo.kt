package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.models.TransactionModel
import kotlinx.coroutines.flow.Flow

interface TransactionLocalRepo {
    fun getAllTransactions(): Flow<List<TransactionModel>>
}

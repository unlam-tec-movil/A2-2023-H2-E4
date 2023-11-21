package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CurrencyEntity
import kotlinx.coroutines.flow.Flow

interface CurrencyRepositoryInterface {
    suspend fun getAllCurrencies(): Flow<List<CurrencyEntity>>
}

package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.models.Currency
import kotlinx.coroutines.flow.Flow

interface CurrencyServiceInterface {
    suspend fun getAllCurrencies(): Flow<List<Currency>>
}

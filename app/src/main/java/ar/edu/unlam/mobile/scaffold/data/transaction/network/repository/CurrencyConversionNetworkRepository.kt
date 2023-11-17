package ar.edu.unlam.mobile.scaffold.data.transaction.network.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.models.CurrencyConversionResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyConversionNetworkRepository {
    suspend fun getCurrencyConversion(
        source: String? = "ARS",
        quantity: String,
    ): Flow<CurrencyConversionResponse>
}

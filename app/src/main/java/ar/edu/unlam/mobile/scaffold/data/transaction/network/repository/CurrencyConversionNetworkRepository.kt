package ar.edu.unlam.mobile.scaffold.data.app.network.repository

import ar.edu.unlam.mobile.scaffold.domain.models.CurrencyConversionResponse
import kotlinx.coroutines.flow.Flow

interface CurrencyConversionNetworkRepository {
    suspend fun getCurrencyConversion(
        source: String,
        target: String,
        format: String,
        quantity: String,
        apiKey: String,
    ): Flow<CurrencyConversionResponse>
}

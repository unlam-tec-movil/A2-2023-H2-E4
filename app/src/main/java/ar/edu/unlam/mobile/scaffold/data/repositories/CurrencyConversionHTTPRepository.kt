package ar.edu.unlam.mobile.scaffold.data.repositories

import ar.edu.unlam.mobile.scaffold.data.network.CurrencyConversionAPI
import ar.edu.unlam.mobile.scaffold.domain.models.CurrencyConversionResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyConversionHTTPRepository
@Inject
constructor(
    private val api: CurrencyConversionAPI,
) : CurrencyConversionNetworkRepository {
    override suspend fun getCurrencyConversion(
        source: String,
        target: String,
        format: String,
        quantity: Double,
        apiKey: String,
    ): Flow<CurrencyConversionResponse> {
        return flow {
            CurrencyConversionResponse(dto = api.getCurrencyConversion(source, target, format, quantity, apiKey))
        }
    }
}

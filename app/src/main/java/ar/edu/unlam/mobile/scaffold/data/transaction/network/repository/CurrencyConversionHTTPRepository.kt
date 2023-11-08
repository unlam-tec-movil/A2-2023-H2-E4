package ar.edu.unlam.mobile.scaffold.data.app.network.repository

import ar.edu.unlam.mobile.scaffold.data.app.network.CurrencyConversionAPI
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
        quantity: String,
        apiKey: String,
    ): Flow<CurrencyConversionResponse> {
        return flow {
            emit(CurrencyConversionResponse(dto = api.getCurrencyConversion(source, target, format, quantity, apiKey)))
        }
    }
}

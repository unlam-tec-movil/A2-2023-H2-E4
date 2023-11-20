package ar.edu.unlam.mobile.scaffold.data.transaction.network.repository

import ar.edu.unlam.mobile.scaffold.data.app.network.CurrencyConversionAPI
import ar.edu.unlam.mobile.scaffold.data.transaction.models.CurrencyConversionResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CurrencyConversionHTTPRepository
@Inject
constructor(
    private val api: CurrencyConversionAPI,
) : CurrencyConversionNetworkRepository {
    override suspend fun getCurrencyConversion(
        source: String?,
        quantity: String,
    ): Flow<CurrencyConversionResponse> {
        return flow {
            emit(
                CurrencyConversionResponse(
                    dto = api.getCurrencyConversion(
                        source = source ?: "ARS",
                        quantity = quantity,
                    ),
                ),
            )
        }
    }
}

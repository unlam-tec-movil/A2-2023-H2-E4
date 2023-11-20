package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.network.repository.CurrencyConversionNetworkRepository
import kotlinx.coroutines.coroutineScope

class CurrencyConversionService(
    private val repository: CurrencyConversionNetworkRepository,
) : CurrencyConversionServiceInterface {
    override suspend fun getCurrencyConversion(
        from: String?,
        quantity: String,
        onSuccess: suspend (Double) -> Unit,
        onFailure: () -> Unit,
    ) {
        coroutineScope {
            repository.getCurrencyConversion(
                source = from,
                quantity = quantity,
            ).collect {
                if (it.status == "OK") {
                    onSuccess(it.result.amount)
                } else {
                    onFailure()
                }
            }
        }
    }
}

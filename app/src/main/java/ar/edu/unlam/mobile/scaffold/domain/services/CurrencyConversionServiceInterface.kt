package ar.edu.unlam.mobile.scaffold.domain.services

interface CurrencyConversionServiceInterface {
    suspend fun getCurrencyConversion(
        from: String? = "ARS",
        quantity: String,
        onSuccess: suspend (Double) -> Unit,
        onFailure: () -> Unit,
    )
}

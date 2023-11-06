package ar.edu.unlam.mobile.scaffold.data.app.network.dto

data class CurrencyConversionResponseDTO(
    val result: ConversionResultDTO? = null,
    val status: String? = null,
)

data class ConversionResultDTO(
    val updated: String? = null,
    val source: String? = null,
    val target: String? = null,
    val value: Double? = null,
    val quantity: Double? = null,
    val amount: Double? = null,
)

package ar.edu.unlam.mobile.scaffold.domain.models

import ar.edu.unlam.mobile.scaffold.data.transaction.network.dto.ConversionResultDTO
import ar.edu.unlam.mobile.scaffold.data.transaction.network.dto.CurrencyConversionResponseDTO

data class CurrencyConversionResponse(
    val result: ConversionResult = ConversionResult(),
    val status: String = "",
) {
    constructor(dto: CurrencyConversionResponseDTO) : this(
        result = ConversionResult(dto.result ?: ConversionResultDTO()),
        status = dto.status ?: "",
    )
}

data class ConversionResult(
    val updated: String = "",
    val source: String = "",
    val target: String = "",
    val value: Double = 0.0,
    val quantity: Double = 0.0,
    val amount: Double = 0.0,
) {
    constructor(dto: ConversionResultDTO) : this(
        updated = dto.updated ?: "",
        source = dto.source ?: "",
        target = dto.target ?: "",
        value = dto.value ?: 0.0,
        quantity = dto.quantity ?: 0.0,
        amount = dto.amount ?: 0.0,
    )
}

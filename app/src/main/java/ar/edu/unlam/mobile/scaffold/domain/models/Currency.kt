package ar.edu.unlam.mobile.scaffold.domain.models

import ar.edu.unlam.mobile.scaffold.data.app.local.core.currency.CurrencyEntity

data class Currency(
    val id: Int,
    val code: String,
    val description: String,
) {
    constructor(entity: CurrencyEntity) : this(
        id = entity.id,
        code = entity.code,
        description = entity.description,
    )
}
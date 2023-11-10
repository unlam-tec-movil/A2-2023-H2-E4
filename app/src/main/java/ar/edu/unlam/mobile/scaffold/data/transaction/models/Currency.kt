package ar.edu.unlam.mobile.scaffold.data.transaction.models

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CurrencyEntity

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

package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.models.Currency

interface CurrencyServiceInterface {
    suspend fun getAllCurrencies(): List<Currency>
}

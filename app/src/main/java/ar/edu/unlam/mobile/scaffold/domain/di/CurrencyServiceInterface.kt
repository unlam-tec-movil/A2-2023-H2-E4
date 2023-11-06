package ar.edu.unlam.mobile.scaffold.domain.di

import ar.edu.unlam.mobile.scaffold.domain.models.Currency

interface CurrencyServiceInterface {
    suspend fun getAllCurrencies(): List<Currency>
}

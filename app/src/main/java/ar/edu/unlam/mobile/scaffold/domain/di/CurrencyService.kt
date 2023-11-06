package ar.edu.unlam.mobile.scaffold.domain.di

import ar.edu.unlam.mobile.scaffold.data.app.local.core.AppDatabase
import ar.edu.unlam.mobile.scaffold.domain.models.Currency
import javax.inject.Inject

class CurrenciesService @Inject constructor(
    private val appDatabase: AppDatabase,
) : CurrencyServiceInterface {
    override suspend fun getAllCurrencies(): List<Currency> {
        return appDatabase.currencyDao().getAllCurrencies().map { Currency(it) }
    }
}

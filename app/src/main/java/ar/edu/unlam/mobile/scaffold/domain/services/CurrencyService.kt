package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.app.local.core.AppDatabase
import ar.edu.unlam.mobile.scaffold.domain.models.Currency
import javax.inject.Inject

class CurrencyService @Inject constructor(
    private val appDatabase: AppDatabase,
) : CurrencyServiceInterface {
    override suspend fun getAllCurrencies(): List<Currency> {
        return appDatabase.currencyDao().getAllCurrencies().map { Currency(it) }
    }
}
// ToDO: Corregir y cambiar el appDatabase por un repository (Tambien crear el repository)
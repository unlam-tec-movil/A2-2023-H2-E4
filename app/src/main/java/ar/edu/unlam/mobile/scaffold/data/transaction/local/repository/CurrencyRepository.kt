package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoCurrency
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CurrencyEntity
import kotlinx.coroutines.flow.Flow

class CurrencyRepository(
    private val daoCurrency: DaoCurrency,
) : CurrencyRepositoryInterface {
    override suspend fun getAllCurrencies(): Flow<List<CurrencyEntity>> {
        return daoCurrency.getAllCurrencies()
    }
}

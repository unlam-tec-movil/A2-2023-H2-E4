package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.toDomain
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.CurrencyRepositoryInterface
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Currency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyService @Inject constructor(
    private val repository: CurrencyRepositoryInterface,
) : CurrencyServiceInterface {
    override suspend fun getAllCurrencies(): Flow<List<Currency>> {
        return repository.getAllCurrencies().map {
            it.map { it ->
                it.toDomain()
            }
        }
    }
}

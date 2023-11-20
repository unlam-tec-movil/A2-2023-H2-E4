package ar.edu.unlam.mobile.scaffold.domain.di

import ar.edu.unlam.mobile.scaffold.data.transaction.local.TransactionDatabase
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoCurrency
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.CurrencyRepository
import ar.edu.unlam.mobile.scaffold.data.transaction.network.repository.CurrencyConversionHTTPRepository
import ar.edu.unlam.mobile.scaffold.domain.services.CurrencyConversionService
import ar.edu.unlam.mobile.scaffold.domain.services.CurrencyConversionServiceInterface
import ar.edu.unlam.mobile.scaffold.domain.services.CurrencyService
import ar.edu.unlam.mobile.scaffold.domain.services.CurrencyServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CurrencyModule {
    @Provides
    fun provideCurrencyService(repository: CurrencyRepository): CurrencyServiceInterface {
        return CurrencyService(repository)
    }

    @Provides
    fun provideCurrencyRepository(daoCurrency: DaoCurrency): CurrencyRepository {
        return CurrencyRepository(daoCurrency)
    }

    @Provides
    fun provideDaoCurrency(appDatabase: TransactionDatabase): DaoCurrency {
        return appDatabase.currencyDao()
    }

    @Provides
    fun providesCurrencyConversionService(repository: CurrencyConversionHTTPRepository): CurrencyConversionServiceInterface {
        return CurrencyConversionService(repository = repository)
    }
}

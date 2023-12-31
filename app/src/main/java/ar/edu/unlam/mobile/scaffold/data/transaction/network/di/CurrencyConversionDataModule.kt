package ar.edu.unlam.mobile.scaffold.data.transaction.network.di

import ar.edu.unlam.mobile.scaffold.data.transaction.network.repository.CurrencyConversionHTTPRepository
import ar.edu.unlam.mobile.scaffold.data.transaction.network.repository.CurrencyConversionNetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CurrencyConversionDataModule {
    @Binds
    abstract fun bindCurrencyConversionNetworkRepo(currencyConversionHTTPClient: CurrencyConversionHTTPRepository): CurrencyConversionNetworkRepository
}

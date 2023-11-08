package ar.edu.unlam.mobile.scaffold.domain.di

import ar.edu.unlam.mobile.scaffold.data.transaction.local.TransactionDatabase
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
    fun provideCurrencyService(appDatabase: TransactionDatabase): CurrencyServiceInterface {
        return CurrencyService(appDatabase)
    }
}

package ar.edu.unlam.mobile.scaffold.domain.di

import ar.edu.unlam.mobile.scaffold.data.app.local.core.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CurrencyModule {
    @Provides
    fun provideCurrencyService(appDatabase: AppDatabase): CurrencyServiceInterface {
        return CurrencyService(appDatabase)
    }
}

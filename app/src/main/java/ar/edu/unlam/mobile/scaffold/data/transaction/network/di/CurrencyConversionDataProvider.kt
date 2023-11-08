package ar.edu.unlam.mobile.scaffold.data.transaction.network.di

import ar.edu.unlam.mobile.scaffold.data.app.network.CurrencyConversionAPI
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CurrencyConversionDataProvider {
    @Provides
    @Singleton
    fun provideCurrencyConversionAPI(gson: Gson): CurrencyConversionAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("http://api.currencies.zone/v1/")
            .build()
            .create(CurrencyConversionAPI::class.java)
    }
}

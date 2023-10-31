package ar.edu.unlam.mobile.scaffold.data.network

import ar.edu.unlam.mobile.scaffold.data.dto.CurrencyConversionResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyConversionAPI {
    @GET("quotes/{source}/{target}/{format}")
    suspend fun getCurrencyConversion(
        @Path("source") source: String,
        @Path("target") target: String,
        @Path("format") format: String,
        @Query("quantity") quantity: Double,
        @Query("key") apiKey: String,
    ): CurrencyConversionResponseDTO
}

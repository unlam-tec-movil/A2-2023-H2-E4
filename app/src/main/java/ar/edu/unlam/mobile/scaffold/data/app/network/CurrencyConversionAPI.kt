package ar.edu.unlam.mobile.scaffold.data.app.network

import ar.edu.unlam.mobile.scaffold.data.transaction.network.dto.CurrencyConversionResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CurrencyConversionAPI {
    @GET("quotes/{source}/{target}/{format}")
    suspend fun getCurrencyConversion(
        @Path("source") source: String,
        @Path("target") target: String = "ARS",
        @Path("format") format: String = "json",
        @Query("quantity") quantity: String,
        @Query("key") apiKey: String = "45717|jb3r*ko06befntG2Ed~oJdD3chm7CfRB",
    ): CurrencyConversionResponseDTO
}

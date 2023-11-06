package ar.edu.unlam.mobile.scaffold.data.app.local.core.currency

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
@Dao
interface DaoCurrency {
    @Insert
    fun insertCurrency(currency: CurrencyEntity)

    @Query("SELECT * FROM Currency")
    fun getAllCurrencies(): List<CurrencyEntity>
}

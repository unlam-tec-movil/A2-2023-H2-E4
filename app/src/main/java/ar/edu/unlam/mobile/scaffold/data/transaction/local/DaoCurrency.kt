package ar.edu.unlam.mobile.scaffold.data.transaction.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CurrencyEntity

@Dao
interface DaoCurrency {

    @Insert
    fun insertCurrency(currency: CurrencyEntity)

    @Query("SELECT * FROM Currency")
    fun getAllCurrencies(): List<CurrencyEntity>
}

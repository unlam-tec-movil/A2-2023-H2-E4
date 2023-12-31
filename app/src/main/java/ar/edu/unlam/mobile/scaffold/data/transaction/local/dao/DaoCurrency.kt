package ar.edu.unlam.mobile.scaffold.data.transaction.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoCurrency {

    @Insert
    fun insertCurrency(currency: CurrencyEntity)

    @Query("SELECT * FROM Currency")
    fun getAllCurrencies(): Flow<List<CurrencyEntity>>
}

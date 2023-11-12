package ar.edu.unlam.mobile.scaffold.data.transaction.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoCategory
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoCurrency
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoTransaction
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CurrencyEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity

@Database(
    entities = [CategoryEntity::class, CurrencyEntity::class, TransactionEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class TransactionDatabase : RoomDatabase() {
    abstract fun categoryDao(): DaoCategory
    abstract fun currencyDao(): DaoCurrency
    abstract fun transactionDao(): DaoTransaction
}

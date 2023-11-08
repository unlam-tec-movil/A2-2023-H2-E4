package ar.edu.unlam.mobile.scaffold.data.transaction.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoCategory
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoColor
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoCurrency
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoTransaction
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoTransactionType
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CurrencyEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionTypeEntity

@Database(
    entities = [CategoryEntity::class, ColorEntity::class, TransactionTypeEntity::class, CurrencyEntity::class, TransactionEntity::class],
    version = 1,
)
abstract class TransactionDatabase : RoomDatabase() {
    abstract fun categoryDao(): DaoCategory
    abstract fun colorDao(): DaoColor
    abstract fun transactionTypeDao(): DaoTransactionType
    abstract fun currencyDao(): DaoCurrency
    abstract fun transactionDao(): DaoTransaction
}

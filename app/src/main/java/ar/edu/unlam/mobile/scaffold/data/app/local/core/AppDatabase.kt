package ar.edu.unlam.mobile.scaffold.data.app.local.core

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffold.data.app.local.core.category.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.app.local.core.category.DaoCategory
import ar.edu.unlam.mobile.scaffold.data.app.local.core.color.ColorEntity
import ar.edu.unlam.mobile.scaffold.data.app.local.core.color.DaoColor
import ar.edu.unlam.mobile.scaffold.data.app.local.core.currency.CurrencyEntity
import ar.edu.unlam.mobile.scaffold.data.app.local.core.currency.DaoCurrency
import ar.edu.unlam.mobile.scaffold.data.app.local.core.transaction.DaoTransaction
import ar.edu.unlam.mobile.scaffold.data.app.local.core.transaction.TransactionEntity
import ar.edu.unlam.mobile.scaffold.data.app.local.core.transactionType.DaoTransactionType
import ar.edu.unlam.mobile.scaffold.data.app.local.core.transactionType.TransactionTypeEntity

@Database(entities = [CategoryEntity::class, ColorEntity::class, TransactionTypeEntity::class, CurrencyEntity::class, TransactionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): DaoCategory
    abstract fun colorDao(): DaoColor
    abstract fun transactionTypeDao(): DaoTransactionType
    abstract fun CurrencyDao(): DaoCurrency
    abstract fun TransactionDao(): DaoTransaction
}

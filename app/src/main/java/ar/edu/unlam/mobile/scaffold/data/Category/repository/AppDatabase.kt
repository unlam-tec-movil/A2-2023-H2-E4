package ar.edu.unlam.mobile.scaffold.data.Category.repository

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CategoryEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun categoryDao(): DaoCategory
}


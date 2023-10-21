package ar.edu.unlam.mobile.scaffold.data.category.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DaoCategory {
    @Query("SELECT * FROM Category")
    fun getAllCategories(): List<CategoryEntity>

    @Insert
    fun insertCategory(category: CategoryEntity): Long

    @Update
    fun updateCategory(category: CategoryEntity)

    @Delete
    fun deleteCategory(category: CategoryEntity)
}

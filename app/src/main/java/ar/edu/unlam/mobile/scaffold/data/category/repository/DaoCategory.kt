package ar.edu.unlam.mobile.scaffold.data.category.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ar.edu.unlam.mobile.scaffold.domain.models.TransactionType

@Dao
interface DaoCategory {

    // trae todas las categorias
    @Query("SELECT * FROM Category")
    fun getAllCategories(): List<CategoryEntity>

    // filtra por type de categorias
    @Query("SELECT * FROM Category WHERE type = :transactionType")
    fun getCategoriesByType(transactionType: TransactionType): List<CategoryEntity>

    @Insert
    fun insertCategory(category: CategoryEntity): Long

    @Update
    fun updateCategory(category: CategoryEntity)

    @Delete
    fun deleteCategory(category: CategoryEntity)
}

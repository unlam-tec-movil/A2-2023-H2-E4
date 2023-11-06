package ar.edu.unlam.mobile.scaffold.data.app.local.core.currency

import androidx.room.Dao

@Dao
interface DaoCurrency {

    // trae todas las categorias
/*    @Query("SELECT * FROM Category")
    fun getAllCategories(): List<CategoryEntity>

    // filtra por type de categorias
    @Query("SELECT * FROM Category WHERE type = :transactionType")
    fun getCategoriesByType(transactionType: TransactionType): List<CategoryEntity>

    @Insert
    fun insertCategory(category: CategoryEntity): Long

    @Update
    fun updateCategory(category: CategoryEntity)

    @Delete
    fun deleteCategory(category: CategoryEntity)*/
}

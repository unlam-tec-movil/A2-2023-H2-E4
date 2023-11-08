package ar.edu.unlam.mobile.scaffold.data.transaction.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity

@Dao
interface DaoCategory {

    // trae todas las categorias
    @Query("SELECT * FROM Category")
    fun getAllCategories(): List<CategoryEntity>

    @Query("SELECT * FROM Category WHERE transaction_type_id = :transactionTypeId")
    fun getCategoriesByTransactionType(transactionTypeId: Int): List<CategoryEntity>

    @Insert
    fun insertCategory(category: CategoryEntity): Long

    @Update
    fun updateCategory(category: CategoryEntity)

    @Delete
    fun deleteCategory(category: CategoryEntity)
}

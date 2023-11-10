package ar.edu.unlam.mobile.scaffold.data.transaction.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryWithDetails
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoCategory {

    // trae todas las categorias
    @Query("SELECT * FROM Category")
    fun getAllCategories(): List<CategoryEntity>

    @Query("SELECT * FROM Category WHERE transaction_type_id = :transactionTypeId")
    fun getCategoriesByTransactionType(transactionTypeId: Int): List<CategoryEntity>

    @Insert
    fun insertCategory(category: CategoryEntity)

    @Update
    fun updateCategory(category: CategoryEntity)

    @Delete
    fun deleteCategory(category: CategoryEntity)

    @Transaction
    @Query(
        "SELECT c.id as id, " +
                "c.id as transaction_type_id, " +
                "c.name as name, " +
                "c.colorString as colorString " +
                "FROM `Category` c " +
                "INNER JOIN `TransactionType` tt ON c.transaction_type_id = tt.id",
    )
    fun getCategory(): Flow<List<CategoryWithDetails>>
}

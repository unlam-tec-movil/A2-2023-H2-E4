package ar.edu.unlam.mobile.scaffold.data.transaction.local.dao
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType
import kotlinx.coroutines.flow.Flow
@Dao
interface DaoCategory {

    @Query("SELECT * FROM Category WHERE transaction_type = :transactionType")
    fun getCategoriesByTransactionType(transactionType: TransactionType): Flow<List<CategoryEntity>>

    @Insert
    fun insertCategory(category: CategoryEntity)

    @Update
    fun updateCategory(category: CategoryEntity)

    @Delete
    fun deleteCategory(category: CategoryEntity)

    @Transaction
    @Query(
        "SELECT * FROM `Category` ",
    )
    fun getCategory(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM Category WHERE id = :id")
    fun getCategoriesById(id: Int): Flow<CategoryEntity>
}

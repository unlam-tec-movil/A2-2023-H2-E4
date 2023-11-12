package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import kotlinx.coroutines.flow.Flow

interface CategoryLocalRepoInterface {
    suspend fun getAllCategory(): Flow<List<CategoryEntity>>
    suspend fun addCategory(category: CategoryEntity)
    suspend fun getCategoriesByType(type: String): Flow<List<CategoryEntity>>
}

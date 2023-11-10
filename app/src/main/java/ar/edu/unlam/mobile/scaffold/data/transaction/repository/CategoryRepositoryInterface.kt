package ar.edu.unlam.mobile.scaffold.data.transaction.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepositoryInterface {
    suspend fun addCategory(category: CategoryEntity)
    suspend fun getAllCategory(): Flow<List<Category>>
}

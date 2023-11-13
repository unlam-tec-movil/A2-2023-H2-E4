package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import kotlinx.coroutines.flow.Flow

interface CategoryServiceInterface {
    suspend fun addCategory(name: String, type: String, colorHex: String)
    suspend fun getAllCategories(): Flow<List<Category>>
    suspend fun getCategoriesByType(type: String): Flow<List<Category>>
}

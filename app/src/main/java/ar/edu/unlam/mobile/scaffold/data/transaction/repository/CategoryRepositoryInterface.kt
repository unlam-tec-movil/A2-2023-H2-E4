package ar.edu.unlam.mobile.scaffold.data.transaction.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.domain.provider.DispatcherProvider
import kotlinx.coroutines.flow.Flow

interface CategoryRepositoryInterface {
    suspend fun addCategory(category: CategoryEntity, dispatcherProvider: DispatcherProvider)
    suspend fun getAllCategory(dispatcherProvider: DispatcherProvider): Flow<List<Category>>
    suspend fun getCategoriesByType(type: String, dispatcherProvider: DispatcherProvider): Flow<List<Category>>
    suspend fun getCategoriesById(id: Int, dispatcherProvider: DispatcherProvider): Flow<Category>
}

package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryWithDetails
import kotlinx.coroutines.flow.Flow

interface CategoryLocalRepoInterface {
    fun getAllCategory(): Flow<List<CategoryWithDetails>>
    fun addCategory(category: CategoryEntity)
}

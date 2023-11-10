package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity

interface CategoryServiceInterface {
    suspend fun insertCategory(category: CategoryEntity)
    suspend fun getAllCategories(): List<CategoryEntity>
}

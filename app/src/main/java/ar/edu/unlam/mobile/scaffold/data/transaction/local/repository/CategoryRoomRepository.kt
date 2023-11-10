package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoCategory
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryWithDetails
import ar.edu.unlam.mobile.scaffold.domain.services.CategoryServiceInterface
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRoomRepository @Inject constructor(
    private val daoCategory: DaoCategory,
) : CategoryLocalRepoInterface, CategoryServiceInterface {
    override fun getAllCategory(): Flow<List<CategoryWithDetails>> {
        return daoCategory.getCategory()
    }

    override fun addCategory(category: CategoryEntity) {
        daoCategory.insertCategory(category)
    }

    override suspend fun insertCategory(category: CategoryEntity) {
        daoCategory.insertCategory(category)
    }

    override suspend fun getAllCategories(): List<CategoryEntity> {
        return daoCategory.getAllCategories()
    }
}

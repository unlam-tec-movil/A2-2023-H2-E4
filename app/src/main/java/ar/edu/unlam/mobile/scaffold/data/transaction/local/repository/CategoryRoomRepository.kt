package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoCategory
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRoomRepository @Inject constructor(
    private val daoCategory: DaoCategory,
) : CategoryLocalRepoInterface {

    override suspend fun getAllCategory(): Flow<List<CategoryEntity>> {
        return daoCategory.getCategory()
    }

    override suspend fun addCategory(category: CategoryEntity) {
        daoCategory.insertCategory(category)
    }
    override suspend fun getCategoriesByType(type: String): Flow<List<CategoryEntity>> {
        return daoCategory.getCategoriesByTransactionType(TransactionType.valueOf(type))
    }

    override suspend fun getCategoriesById(id: Int): Flow<CategoryEntity> {
        return daoCategory.getCategoriesById(id)
    }
}

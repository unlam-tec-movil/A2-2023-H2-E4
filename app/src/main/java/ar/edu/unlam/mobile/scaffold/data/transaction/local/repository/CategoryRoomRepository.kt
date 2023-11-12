package ar.edu.unlam.mobile.scaffold.data.transaction.local.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoCategory
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
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
}

package ar.edu.unlam.mobile.scaffold.data.transaction.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.CategoryLocalRepoInterface
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryDefaultRepository @Inject constructor(
    private val categoryLocalRepo: CategoryLocalRepoInterface,
) : CategoryRepositoryInterface {
    override suspend fun addCategory(category: CategoryEntity) {
        categoryLocalRepo.addCategory(category)
    }

    override suspend fun getAllCategory(): Flow<List<Category>> {
        return categoryLocalRepo.getAllCategory().map { list ->
            list.map {
                it.toDomain()
            }
        }
    }
}

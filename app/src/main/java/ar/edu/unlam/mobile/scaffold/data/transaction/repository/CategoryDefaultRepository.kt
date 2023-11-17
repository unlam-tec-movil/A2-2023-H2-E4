package ar.edu.unlam.mobile.scaffold.data.transaction.repository

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.CategoryLocalRepoInterface
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.domain.provider.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryDefaultRepository @Inject constructor(
    private val categoryLocalRepo: CategoryLocalRepoInterface,
) : CategoryRepositoryInterface {

    override suspend fun addCategory(category: CategoryEntity, dispatcherProvider: DispatcherProvider) {
        categoryLocalRepo.addCategory(category)
    }

    override suspend fun getAllCategory(dispatcherProvider: DispatcherProvider): Flow<List<Category>> {
        return categoryLocalRepo.getAllCategory()
            .map { list ->
                list.map { it.toDomain() }
            }
    }
    override suspend fun getCategoriesByType(
        type: String,
        dispatcherProvider: DispatcherProvider,
    ): Flow<List<Category>> {
        return categoryLocalRepo.getCategoriesByType(type)
            .map { list ->
                list.map { it.toDomain() }
            }
    }
    override suspend fun getCategoriesById(
        id: Int,
        dispatcherProvider: DispatcherProvider,
    ): Flow<Category> {
        return categoryLocalRepo.getCategoriesById(id)
            .map { categoryEntity ->
                categoryEntity?.toDomain() ?: throw NoSuchElementException("Category not found")
            }
            .flowOn(dispatcherProvider.io)
    }
}

package ar.edu.unlam.mobile.scaffold.domain.services

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType
import ar.edu.unlam.mobile.scaffold.data.transaction.repository.CategoryRepositoryInterface
import ar.edu.unlam.mobile.scaffold.domain.provider.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryServiceImpl @Inject constructor(
    private val categoryRepository: CategoryRepositoryInterface,
    private val dispatcherProvider: DispatcherProvider,
) : CategoryServiceInterface {

    override suspend fun addCategory(name: String, type: String, colorHex: String) {
        val newCategory = CategoryEntity(
            id = 0,
            categoryTransactionType = TransactionType.valueOf(type),
            name = name,
            colorString = colorHex,
        )
        categoryRepository.addCategory(newCategory, dispatcherProvider)
    }

    override suspend fun getAllCategories(): Flow<List<Category>> =
        categoryRepository.getAllCategory(dispatcherProvider)
            .map { list ->
                list.map { it.toDomain() }
            }
  
    override suspend fun getCategoriesByType(type: String): Flow<List<Category>> =
        categoryRepository.getCategoriesByType(type, dispatcherProvider)
            .map { list ->
                list.map { it.toDomain() }
            }
}

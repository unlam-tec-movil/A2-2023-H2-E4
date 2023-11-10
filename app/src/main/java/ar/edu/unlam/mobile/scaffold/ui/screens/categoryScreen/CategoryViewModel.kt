package ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.domain.services.CategoryServiceInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
@Inject
constructor(private val categoryService: CategoryServiceInterface) : ViewModel() {

    // Agregar una categoría a la base de datos
    fun addCategory(name: String, type: String, colorHex: String) {
        // Inserta categorías en la base de datos usando una corutina
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val newCategory = CategoryEntity(
                    id = 0, // Room generará automáticamente un ID único
                    categoryTransactionTypeId = 1,
                    name = name,
                    colorString = "rojo",
                )
                categoryService.insertCategory(newCategory)
                var myCategories = categoryService.getAllCategories()
                print(myCategories)
            }
        }
    }
}
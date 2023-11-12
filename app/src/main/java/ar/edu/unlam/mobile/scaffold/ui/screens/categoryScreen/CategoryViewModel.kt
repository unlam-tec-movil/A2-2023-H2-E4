package ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.domain.services.CategoryServiceInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
@Inject constructor(private val categoryService: CategoryServiceInterface) : ViewModel() {

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> get() = _categories

    // Agregar una categoría a la base de datos
    fun addCategory(name: String, type: String, colorHex: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                categoryService.addCategory(name, type, colorHex)
            }
        }
    }

    // Cargar todas las categorías
    fun loadCategories() {
        viewModelScope.launch {
            categoryService.getAllCategories()
                .catch { /* Manejar errores, si es necesario */ }
                .collect { categories ->
                    _categories.value = categories.map { it.toDomain() }
                }
        }
    }
}

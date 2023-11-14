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

    private val _selectedCategory = MutableStateFlow<Category?>(null)
    val selectedCategory: StateFlow<Category?> get() = _selectedCategory

    private var lastSelectedType: String = "Ingresos" // Valor predeterminado
    fun addCategory(name: String, type: String, colorHex: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                categoryService.addCategory(name, type, colorHex)
                lastSelectedType = type // Actualiza la última opción seleccionada
                loadCategories() // Cargar categorías filtradas después de agregar
            }
        }
    }
    fun getAllCategories() {
        viewModelScope.launch {
            categoryService.getAllCategories()
                .catch { /* Manejar errores, si es necesario */ }
                .collect { categories ->
                    _categories.value = categories.map { it.toDomain() }
                }
        }
    }
    fun getCategoriesByType(type: String) {
        viewModelScope.launch {
            categoryService.getCategoriesByType(type)
                .catch { /* Manejar errores, si es necesario */ }
                .collect { categories ->
                    _categories.value = categories.map { it.toDomain() }
                }
        }
    }
    fun loadCategories() {
        getCategoriesByType(lastSelectedType)
    }

    fun setSelectedCategory(category: Category) {
        _selectedCategory.value = category
    }
}

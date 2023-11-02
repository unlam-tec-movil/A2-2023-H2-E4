package ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.category.repository.AppDatabase
import ar.edu.unlam.mobile.scaffold.data.category.repository.CategoryEntity
import ar.edu.unlam.mobile.scaffold.domain.models.TransactionType
import ar.edu.unlam.mobile.scaffold.ui.components.category.getColorCategoryFromHex
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
@Inject
constructor(val appDatabase: AppDatabase) : ViewModel() {

    // Agregar una categoría a la base de datos
    fun addCategoryToDatabase(name: String, type: String, colorHex: String) {
        // Inserta categorías en la base de datos usando una corutina
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val newCategory = CategoryEntity(
                    id = 0, // Room generará automáticamente un ID único
                    type = if (type == "income") {
                        TransactionType.INCOME
                    } else {
                        TransactionType.EXPENSE
                    },
                    name = name,
                    color = getColorCategoryFromHex(colorHex),
                )
                appDatabase.categoryDao().insertCategory(newCategory)
                var myCategories = appDatabase.categoryDao().getAllCategories()
                print(myCategories)
            }
        }
    }
}

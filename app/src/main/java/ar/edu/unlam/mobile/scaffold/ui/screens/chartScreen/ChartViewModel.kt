package ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.category.repository.AppDatabase
import ar.edu.unlam.mobile.scaffold.data.category.repository.CategoryEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChartViewModel
@Inject
constructor(val appDatabase: AppDatabase) : ViewModel() {
    var categoryList: List<CategoryEntity> by mutableStateOf(listOf())

    fun getCategoryList() {
        viewModelScope.launch {
            val myCategories = appDatabase.categoryDao().getAllCategories()
            categoryList = myCategories
        }
    }
}

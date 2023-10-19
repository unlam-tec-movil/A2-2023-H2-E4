package ar.edu.unlam.mobile.scaffold.ui.screens.CategoryScreen

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffold.data.Category.repository.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
    @Inject constructor(val appDatabase: AppDatabase) : ViewModel() {
}
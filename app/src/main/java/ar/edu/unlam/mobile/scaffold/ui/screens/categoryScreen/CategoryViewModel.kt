package ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffold.data.category.repository.AppDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel
@Inject constructor(val appDatabase: AppDatabase) : ViewModel()

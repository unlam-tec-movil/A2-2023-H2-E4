package ar.edu.unlam.mobile.scaffold.ui.screens.transactionScreen

import androidx.lifecycle.ViewModel
import ar.edu.unlam.mobile.scaffold.domain.models.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TransactionScreenViewModel @Inject constructor(
//    private val repository: TransactionRepository
) : ViewModel() {
    private val _selectedTab = MutableStateFlow(TransactionType.EXPENSE)
    val selectedTab: StateFlow<TransactionType> = _selectedTab

    fun changeTab(tabType: TransactionType) {
        _selectedTab.value = tabType
    }
}

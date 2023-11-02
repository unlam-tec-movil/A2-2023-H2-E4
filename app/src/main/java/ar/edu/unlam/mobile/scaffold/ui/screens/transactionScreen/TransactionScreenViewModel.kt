package ar.edu.unlam.mobile.scaffold.ui.screens.transactionScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.repositories.CurrencyConversionHTTPRepository
import ar.edu.unlam.mobile.scaffold.domain.models.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionScreenViewModel @Inject constructor(
    private val repository: CurrencyConversionHTTPRepository,
) : ViewModel() {
    private val _selectedTab = MutableStateFlow(TransactionType.EXPENSE)
    val selectedTab: StateFlow<TransactionType> = _selectedTab

    private val _convertedValue = MutableStateFlow("0")
    val convertedValue: MutableStateFlow<String> = _convertedValue
//    val apiKey = BuildConfig

    fun changeTab(tabType: TransactionType) {
        _selectedTab.value = tabType
    }
    fun getCurrencyConversion(source: String, target: String, format: String = "json", quantity: String, apiKey: String = "45717|jb3r*ko06befntG2Ed~oJdD3chm7CfRB") {
        viewModelScope.launch {
            repository.getCurrencyConversion(source, target, format, quantity, apiKey).collect {
                if (it.status == "OK") {
                    val convertedValue = it.result.amount
                    _convertedValue.value = "El valor es $convertedValue"
                } else {
                    _convertedValue.value = "Error en la conversión"
                }
            }
        }
    }
}

package ar.edu.unlam.mobile.scaffold.ui.screens.transactionScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.app.network.repository.CurrencyConversionHTTPRepository
import ar.edu.unlam.mobile.scaffold.domain.di.CurrencyServiceInterface
import ar.edu.unlam.mobile.scaffold.domain.models.Currency
import ar.edu.unlam.mobile.scaffold.domain.models.TransactionType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransactionScreenViewModel @Inject constructor(
    private val repository: CurrencyConversionHTTPRepository,
    private val currencyService: CurrencyServiceInterface
) : ViewModel() {
    private val _selectedTab = MutableStateFlow(TransactionType.EXPENSE)
    val selectedTab: StateFlow<TransactionType> = _selectedTab

    private val _convertedValue = MutableStateFlow("0")
    val convertedValue: MutableStateFlow<String> = _convertedValue

    private val _currencies = mutableStateOf<List<Currency>>(emptyList())
    val currencies: State<List<Currency>> = _currencies

    private val _selectedCurrency = MutableStateFlow("ARS")
    val selectedCurrency: StateFlow<String> = _selectedCurrency

//    val apiKey = BuildConfig

    init {
        // Cargar las monedas en la inicialización del ViewModel
        viewModelScope.launch {
            val loadedCurrencies = loadCurrencies()
            _currencies.value = loadedCurrencies
        }
    }

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
    suspend fun loadCurrencies(): List<Currency> = withContext(Dispatchers.IO) {
        return@withContext currencyService.getAllCurrencies()
    }
}

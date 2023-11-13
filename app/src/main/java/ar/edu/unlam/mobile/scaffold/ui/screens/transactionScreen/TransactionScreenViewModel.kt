package ar.edu.unlam.mobile.scaffold.ui.screens.transactionScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Currency
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType
import ar.edu.unlam.mobile.scaffold.data.transaction.network.repository.CurrencyConversionHTTPRepository
import ar.edu.unlam.mobile.scaffold.domain.services.CategoryServiceInterface
import ar.edu.unlam.mobile.scaffold.domain.services.CurrencyServiceInterface
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionServiceInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TransactionScreenViewModel @Inject constructor(
    private val repository: CurrencyConversionHTTPRepository,
    private val currencyService: CurrencyServiceInterface,
    private val transactionService: TransactionServiceInterface,
    private val categoryService: CategoryServiceInterface,
) : ViewModel() {
    private val _selectedTab = MutableStateFlow(TransactionType.Gastos)
    val selectedTab: MutableStateFlow<TransactionType> = _selectedTab

    private val _convertedValue = MutableStateFlow("0")
    val convertedValue: MutableStateFlow<String> = _convertedValue

    private val _currencies = mutableStateOf<List<Currency>>(emptyList())
    val currencies: State<List<Currency>> = _currencies

    private val _selectedCurrency = MutableStateFlow("ARS")
    val selectedCurrency: StateFlow<String> = _selectedCurrency

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: MutableStateFlow<List<Category>> = _categories

//    val apiKey = BuildConfig

    init {
        // Cargar las monedas en la inicialización del ViewModel
        viewModelScope.launch {
            val loadedCurrencies = loadCurrencies()
            _currencies.value = loadedCurrencies

            loadCategories(selectedTab.value).collect { loadedCategories ->
                _categories.value = loadedCategories
            }
        }
    }

    fun changeTab(tabType: TransactionType) {
        _selectedTab.value = tabType
        viewModelScope.launch {
            loadCategories(tabType).collect { loadedCategories ->
                _categories.value = loadedCategories
            }
        }
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

    fun createNewTransaction(type: TransactionType, category: Category, currency: Currency, amount: Double, date: String, description: String) {
        val transaction = Transaction(
            id = 0,
            type = type,
            category = category,
            currency = currency,
            amount = amount,
            date = date,
            description = description,
        )
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                transactionService.insertTransaction(transaction)
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
    suspend fun loadCurrencies(): List<Currency> = withContext(Dispatchers.IO) {
        return@withContext currencyService.getAllCurrencies()
    }
    suspend fun loadCategories(selectedTab: TransactionType): Flow<List<Category>> = withContext(Dispatchers.IO) {
        return@withContext categoryService.getCategoriesByType(selectedTab.name)
    }
}

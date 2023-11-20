package ar.edu.unlam.mobile.scaffold.ui.screens.addTransactionScreen

import android.util.Log
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Currency
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType
import ar.edu.unlam.mobile.scaffold.domain.services.CategoryServiceInterface
import ar.edu.unlam.mobile.scaffold.domain.services.CurrencyConversionServiceInterface
import ar.edu.unlam.mobile.scaffold.domain.services.CurrencyServiceInterface
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionServiceInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Immutable
sealed interface TransactionScreenUIState {
    data class Success(
        val categories: List<Category>,
        val currencies: List<Currency>,
        val selectedCurrency: Currency?,
    ) : TransactionScreenUIState

    object Loading : TransactionScreenUIState
    data class Error(val message: String) : TransactionScreenUIState
}
sealed interface TransactionButtonState {
    object Loading : TransactionButtonState
    object Finished : TransactionButtonState
}
data class TransactionScreenViewState(
    val transactionScreenState: TransactionScreenUIState = TransactionScreenUIState.Loading,
)

@HiltViewModel
class AddTransactionScreenViewModel @Inject constructor(
    private val currencyConversionService: CurrencyConversionServiceInterface,
    private val currencyService: CurrencyServiceInterface,
    private val transactionService: TransactionServiceInterface,
    private val categoryService: CategoryServiceInterface,
) : ViewModel() {
    // Variables que almacenan el estado de la pantalla
    private val _transactionScreenUIState =
        mutableStateOf<TransactionScreenUIState>(TransactionScreenUIState.Loading)
    val transactionScreenUIState: State<TransactionScreenUIState> = _transactionScreenUIState

    private val _transactionButtonState =
        mutableStateOf<TransactionButtonState>(TransactionButtonState.Finished)
    val transactionButtonState: State<TransactionButtonState> = _transactionButtonState
    private val _amount = mutableStateOf("")
    val amount: State<String> = _amount

    private val _convertedValue = mutableStateOf(0.0)
    val convertedValue: State<Double> = _convertedValue

    private val _selectedCurrency = mutableStateOf<Currency?>(null)
    val selectedCurrency: State<Currency?> = _selectedCurrency

    private val _selectedCategory = mutableStateOf<Category?>(null)
    val selectedCategory: State<Category?> = _selectedCategory

    private val _comment = mutableStateOf("")
    val comment: State<String> = _comment

    private val _selectedTab = mutableStateOf<TransactionType>(TransactionType.Gastos)
    val selectedTab: State<TransactionType> = _selectedTab

    private val _isButtonEnabled = mutableStateOf(false)
    val isButtonEnabled: State<Boolean> = _isButtonEnabled

    val tabs = TransactionType.values().toList()

    suspend fun loadData() {
        try {
            _transactionScreenUIState.value = TransactionScreenUIState.Loading

            val categories = withContext(Dispatchers.IO) {
                categoryService.getCategoriesByType(selectedTab.value.name)
            }
            val loadedCurrencies = withContext(Dispatchers.IO) {
                currencyService.getAllCurrencies()
            }

            categories.collect { categories ->
                val arsCurrency = loadedCurrencies.find { it.code == "ARS" }
                _selectedCurrency.value = arsCurrency
                _transactionScreenUIState.value = TransactionScreenUIState.Success(
                    categories = categories,
                    currencies = loadedCurrencies,
                    selectedCurrency = arsCurrency,
                )
            }
        } catch (e: Exception) {
            // Manejar errores si es necesario
            _transactionScreenUIState.value = TransactionScreenUIState.Error("$e.message")
        }
    }

    fun insertTransaction() {
        _transactionButtonState.value = TransactionButtonState.Loading

        viewModelScope.launch {
            try {
                currencyConversionService.getCurrencyConversion(
                    selectedCurrency.value?.code,
                    amount.value,
                    onSuccess = { amount ->
                        transactionService.insertTransaction(
                            selectedTab.value,
                            selectedCategory.value,
                            selectedCurrency.value,
                            amount,
                            comment.value,
                        )
                    },
                    onFailure = {
                        print("Error de conversion")
                    },
                )
            } catch (e: Exception) {
                Log.e("Error", e.message.toString(), e)
            } finally {
                _transactionButtonState.value = TransactionButtonState.Finished
                _amount.value = ""
                _comment.value = ""
            }
        }
    }

    // Funciones de cambio de valor

    fun setSelectedCurrency(currency: Currency) {
        _selectedCurrency.value = currency
    }

    fun setTab(value: TransactionType) {
        _selectedTab.value = value
        viewModelScope.launch {
            loadData()
        }
    }

    fun setConvertedValue(value: String) {
        _convertedValue.value = value.toDouble()
    }

    fun setAmount(value: String) {
        _amount.value = value
        updateButtonEnabledState()
    }

    fun setComment(value: String) {
        _comment.value = value
        updateButtonEnabledState()
    }

    private fun updateButtonEnabledState() {
        _isButtonEnabled.value =
            _amount.value.isNotEmpty() && _comment.value.isNotEmpty() && _selectedCategory.value != null
    }
    fun setSelectedCategory(category: Category) {
        _selectedCategory.value = category
        updateButtonEnabledState()
    }
    fun getCategoriesById(id: Int) {
        viewModelScope.launch {
            try {
                var category: Category = categoryService.getCategoriesById(id).firstOrNull()!!
                category.let { setSelectedCategory(it) }
            } catch (e: Exception) {
                // Manejar errores si es necesario
                // _selectedCategory.value = null
            }
        }
    }
}

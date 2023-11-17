package ar.edu.unlam.mobile.scaffold.ui.screens.addTransactionScreen

import android.util.Log
import androidx.compose.runtime.Immutable
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
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.singleOrNull
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

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

data class TransactionScreenViewState(
    val transactionScreenState: TransactionScreenUIState = TransactionScreenUIState.Loading,
)

@HiltViewModel
class AddTransactionScreenViewModel @Inject constructor(
    private val repository: CurrencyConversionHTTPRepository,
    private val currencyService: CurrencyServiceInterface,
    private val transactionService: TransactionServiceInterface,
    private val categoryService: CategoryServiceInterface,
) : ViewModel() {
    // Variables que almacenan el estado de la pantalla
    private val _transactionScreenUIState =
        mutableStateOf<TransactionScreenUIState>(TransactionScreenUIState.Loading)
    val transactionScreenUIState: State<TransactionScreenUIState> = _transactionScreenUIState

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
        viewModelScope.launch {
            try {
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                val current = LocalDateTime.now().format(formatter)
                // aca deberia actualizar el amount y transformarlo a pesos argentinos.

                repository.getCurrencyConversion(
                    selectedCurrency.value!!.code,
                    "ARS",
                    "json",
                    amount.value,
                    "45717|jb3r*ko06befntG2Ed~oJdD3chm7CfRB",
                ).collect {
                    if (it.status == "OK") {
                        val convertedValue = it.result.amount
                        transactionService.insertTransaction(
                            Transaction(
                                id = 0,
                                type = selectedTab.value,
                                category = selectedCategory.value ?: Category(
                                    0,
                                    TransactionType.Gastos,
                                    "Sin categoria",
                                    "FFFFFF",
                                ),
                                currency = selectedCurrency.value ?: Currency(
                                    0,
                                    "ARS",
                                    "Peso Argentino",
                                ),
                                amount = convertedValue,
                                date = current,
                                description = comment.value,
                            ),
                        )
                    } else {
                        print("Error de conversion")
                    }
                }
            } catch (e: Exception) {
                // Manejar errores si es necesario
                Log.e("Error", e.message.toString(), e)
                print("Hubo un error")
            } finally {
                _amount.value = ""
                _comment.value = ""
            }
        }
    }

    fun getCurrencyConversion(
        source: String,
        target: String,
        format: String = "json",
        quantity: String,
        apiKey: String = "45717|jb3r*ko06befntG2Ed~oJdD3chm7CfRB",
    ) {
        viewModelScope.launch {
        }
    }

    // Funciones de cambio de valor

    fun setSelectedCurrency(currency: Currency) {
        _selectedCurrency.value = currency
    }

    fun setSelectedCategory(category: Category) {
        _selectedCategory.value = category
        updateButtonEnabledState()
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
    suspend fun obtenerCategoriaDeFormaSincrona(tuId: Int): Category? {
        return suspendCoroutine { continuation ->
            viewModelScope.launch {
                val result = getCategoriesById(tuId)
                continuation.resume(result)
            }
        }
    }

    private suspend fun getCategoriesById(id: Int): Category? {
        return categoryService.getCategoriesById(id)
            .catch { /* Manejar errores, si es necesario */ }
            .map { it?.toDomain() } // Usar it?.toDomain() para manejar el caso de null
            .singleOrNull()
    }
}

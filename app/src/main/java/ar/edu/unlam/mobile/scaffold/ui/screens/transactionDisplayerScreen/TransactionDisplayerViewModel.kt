package ar.edu.unlam.mobile.scaffold.ui.screens.transactionDisplayerScreen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionServiceInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TransactionDisplayerScreenUIState {
    data class Success(
        val transactions: List<Transaction>,
    ) : TransactionDisplayerScreenUIState

    object Loading : TransactionDisplayerScreenUIState
    data class Error(val message: String) : TransactionDisplayerScreenUIState
}

@HiltViewModel
class TransactionDisplayerViewModel @Inject constructor(
    private val transactionService: TransactionServiceInterface,
) : ViewModel() {
    // Variables
    private val _screenState = mutableStateOf<TransactionDisplayerScreenUIState>(TransactionDisplayerScreenUIState.Loading)
    val screenState: State<TransactionDisplayerScreenUIState> = _screenState

    suspend fun loadData() {
        viewModelScope.launch {
            try {
                _screenState.value = TransactionDisplayerScreenUIState.Loading

                val transactionFlow = transactionService.getAllTransactions()

                transactionFlow.collect { transactions ->
                    _screenState.value = TransactionDisplayerScreenUIState.Success(transactions)
                }
            } catch (e: Exception) {
                // Manejar errores si es necesario
                _screenState.value = TransactionDisplayerScreenUIState.Error("$e.message")
            }
        }
    }
}

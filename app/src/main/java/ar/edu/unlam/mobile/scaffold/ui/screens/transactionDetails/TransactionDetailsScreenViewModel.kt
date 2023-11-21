package ar.edu.unlam.mobile.scaffold.ui.screens.transactionDetails

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionServiceInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TransactionDetailsScreenUIState {
    data class Success(
        val transaction: Transaction,
    ) : TransactionDetailsScreenUIState

    object Loading : TransactionDetailsScreenUIState
    data class Error(val message: String) : TransactionDetailsScreenUIState
}

@HiltViewModel
class TransactionDetailsScreenViewModel @Inject constructor(
    private val transactionService: TransactionServiceInterface,
) : ViewModel() {
    private val _screenState = mutableStateOf<TransactionDetailsScreenUIState>(TransactionDetailsScreenUIState.Loading)
    val screenState: State<TransactionDetailsScreenUIState> = _screenState

    private val _transactionId = mutableStateOf(0)
    val transactionId: State<Int> = _transactionId

    private val _transaction = mutableStateOf<Transaction?>(null)
    val transaction: State<Transaction?> = _transaction

    suspend fun loadData() {
        viewModelScope.launch {
            try {
                _screenState.value = TransactionDetailsScreenUIState.Loading

                val transactionFlow = transactionService.getTransactionById(_transactionId.value)

                transactionFlow.collect { transaction ->
                    _transaction.value = transaction
                    _screenState.value = TransactionDetailsScreenUIState.Success(transaction = transaction)
                }
            } catch (e: Exception) {
                // Manejar errores si es necesario
                _screenState.value = TransactionDetailsScreenUIState.Error("$e.message")
            }
        }
    }

    fun setTransactionId(id: Int) {
        _transactionId.value = id
    }
}

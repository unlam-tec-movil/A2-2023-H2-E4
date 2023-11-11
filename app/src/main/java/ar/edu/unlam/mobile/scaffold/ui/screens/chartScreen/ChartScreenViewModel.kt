package ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.transaction.models.PieChartInput
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionServiceInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class ChartScreenViewModel @Inject constructor(
    private val transactionService: TransactionServiceInterface
) :ViewModel(){
    private val transactionsValue = MutableStateFlow<List<Transaction>>(emptyList())
    private var pieCharInputList by mutableStateOf(emptyList<PieChartInput>())

    init {
        viewModelScope.launch {
            val loadTransaction = loadTransaction()
            transactionsValue.value = loadTransaction
        }
        viewModelScope.launch {
            pieCharInputList = calculateTotalAmountPerCategory()
        }
    }

    suspend fun loadTransaction() : List<Transaction> = withContext(Dispatchers.IO){
        return@withContext transactionService.getAllTransaction()
    }

    suspend fun calculateTotalAmountPerCategory() : MutableList<PieChartInput> {
        return transactionsValue.first().groupBy { it.category }
            .mapTo(mutableListOf()) { (category, transactions) ->
                PieChartInput(category, transactions.sumOf { it.amount })
            }
    }
    fun calcularPorcentaje(item: PieChartInput): Int {
        var total = 0.0
        pieCharInputList.forEach {
            total+=it.totalAmount
        }
        return ((item.totalAmount).div(total).times(100)).roundToInt()
    }
}



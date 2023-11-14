package ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.transaction.models.PieChartInput
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionServiceInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class ChartScreenViewModel @Inject constructor(
    private val transactionService: TransactionServiceInterface,
) : ViewModel() {

    private val transactionsValue = MutableStateFlow<List<Transaction>>(emptyList())
    val transaction: MutableStateFlow<List<Transaction>> get() = transactionsValue

    val pieCharInputList = MutableStateFlow<List<PieChartInput>>(emptyList())

    init {
        viewModelScope.launch {
            loadTransaction()
        }
    }

    private fun calculateTotalAmountPerCategory(): List<PieChartInput> {
        return transactionsValue.value
            .filter { it.type == TransactionType.Gastos }
            .groupBy { it.category }
            .map { (category, transactions) ->
                PieChartInput(category, transactions.sumOf { it.amount })
            }
    }

    private fun loadDatePieChartList() {
        pieCharInputList.value = calculateTotalAmountPerCategory()
    }

    suspend fun loadTransaction() {
        transactionService.getAllTransactions()
            .catch { exception ->
                println("Error al obtener transacciones: $exception")
            }
            .collect { listaDetransacciones ->
                transactionsValue.value = listaDetransacciones.map { it.toDomain() }
                loadDatePieChartList()
            }
    }

    fun calcularPorcentaje(item: PieChartInput): Int {
        var total = 0.0
        pieCharInputList.value.forEach {
            total += it.totalAmount
        }
        return ((item.totalAmount).div(total).times(100)).roundToInt()
    }
}

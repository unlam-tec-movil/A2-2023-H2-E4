package ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import ar.edu.unlam.mobile.scaffold.data.transaction.models.PieChartInput
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.domain.provider.DispatcherProvider
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionServiceInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class ChartScreenViewModel @Inject constructor(
    private val transactionService: TransactionServiceInterface
) : ViewModel() {

    private val transactionsValue = MutableStateFlow<List<Transaction>>(emptyList())
    val transaction : MutableStateFlow<List<Transaction>> get() = transactionsValue

    val pieCharInputList = MutableStateFlow<List<PieChartInput>>(emptyList())
    //val listaDeInptForCharScreen: MutableStateFlow<List<PieChartInput>> get() = pieCharInputList

    init {
        loadTransaction()
        println(transactionsValue)

        viewModelScope.launch {
            val pieChartInput = calculateTotalAmountPerCategory()
            pieCharInputList.value = pieChartInput
        }
    }

   /* suspend fun loadTransaction() : Flow<List<Transaction>> = withContext(Dispatchers.IO){
        val result =  transactionService.getAllTransactions()
        println(result)
        return@withContext result
    }*/

    fun loadTransaction()  {
        viewModelScope.launch {
            transactionService.getAllTransactions()
                .catch { exception ->
                    // Maneja la excepción
                    println("Error al obtener transacciones: $exception")
                }
                .collect { listaDetransacciones ->
                    transactionsValue.value = listaDetransacciones.map { it.toDomain() }
                    println(transactionsValue.value)
                }
        }
    }

    private fun calculateTotalAmountPerCategory(): List<PieChartInput> {
        return transactionsValue.value.groupBy { it.category }
            .map { (category, transactions) ->
                PieChartInput(category, transactions.sumOf { it.amount })
            }
    }

    private fun loadDatePieChartList() {
        viewModelScope.launch {
            pieCharInputList.value = calculateTotalAmountPerCategory()
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

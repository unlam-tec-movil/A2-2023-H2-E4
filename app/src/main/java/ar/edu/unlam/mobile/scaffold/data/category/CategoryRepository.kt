package ar.edu.unlam.mobile.scaffold.data.category

import androidx.compose.ui.graphics.Color
import ar.edu.unlam.mobile.scaffold.data.TransactionType
import kotlin.math.roundToInt

object CategoryRepository {
    private val categories = mutableListOf(
        Category(1, TransactionType.Expense,"Ropa", 5000.0, Color.Magenta),
        Category(2, TransactionType.Expense,"Comida",20000.0, Color.Cyan),
        Category(3, TransactionType.Expense,"Limpieza",1000.0, Color.Green)
    )

    fun order(): List<Category> {
        return categories.sortedByDescending { it.totalAmount }
    }

    fun exist(name: String): Boolean {
        return (categories.any { category: Category -> category.name == name })
    }

    fun listOfNames(): List<String> {
        val names = mutableListOf<String>()
        categories.forEach {
            category: Category -> names.add(category.name)
        }
        return names
    }

    private fun total(): Double {
        var total = 0.0
        categories.forEach {
            total += it.totalAmount
        }
        return total
    }

    fun porcentaje(category: Category): Int {
        return ((category.totalAmount.div(total())).times(100)).roundToInt()
    }
}

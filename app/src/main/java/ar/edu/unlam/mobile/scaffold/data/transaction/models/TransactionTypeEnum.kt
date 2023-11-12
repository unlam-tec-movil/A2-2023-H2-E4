package ar.edu.unlam.mobile.scaffold.data.transaction.models

enum class TransactionTypeEnum(val id: Int, val description: String) {
    INCOME(0, "Expense"),
    EXPENSE(1, "Income"),
    ;

    companion object {
        fun fromInt(value: Int): TransactionType {
            return when (value) {
                INCOME.id -> TransactionType(INCOME.id, INCOME.description)
                EXPENSE.id -> TransactionType(EXPENSE.id, EXPENSE.description)
                // Agrega casos para otros valores si es necesario
                else -> throw IllegalArgumentException("Valor no válido para TransactionType")
            }
        }
    }
}

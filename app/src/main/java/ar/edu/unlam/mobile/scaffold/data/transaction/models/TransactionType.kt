package ar.edu.unlam.mobile.scaffold.domain.models

enum class TransactionType(val typeCode: Int) {
    EXPENSE(0),
    INCOME(1);

    companion object {
        private val map = values().associateBy(TransactionType::typeCode)
        fun fromInt(code: Int) = map[code]
    }
}


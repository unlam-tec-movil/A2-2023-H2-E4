package ar.edu.unlam.mobile.scaffold.data.transaction.models

data class Category(
    var id: Int = 0,
    var type: TransactionType,
    var name: String = "",
    var color: String = "",
)

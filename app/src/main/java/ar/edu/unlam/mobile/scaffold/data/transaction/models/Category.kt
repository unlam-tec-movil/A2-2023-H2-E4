package ar.edu.unlam.mobile.scaffold.data.transaction.models

data class Category(
    var id: Int,
    var type: TransactionType,
    var name: String,
    var color: String,
)

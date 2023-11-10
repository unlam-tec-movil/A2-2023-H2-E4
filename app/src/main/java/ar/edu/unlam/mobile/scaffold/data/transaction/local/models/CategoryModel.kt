package ar.edu.unlam.mobile.scaffold.data.transaction.local.models

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionTypeEntity

class CategoryModel(
    var id: Int,
    var type: TransactionTypeEntity,
    var name: String,
    var color: String,
)

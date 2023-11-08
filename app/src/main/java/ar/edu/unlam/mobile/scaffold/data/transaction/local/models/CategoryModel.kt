package ar.edu.unlam.mobile.scaffold.data.transaction.local.models

import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.TransactionTypeEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.toDomain
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category

class CategoryModel(
    var id: Int,
    var type: TransactionTypeEntity,
    var name: String,
    var color: String,
)
fun CategoryModel.toDomain() = Category(
    id = id,
    type = type.toDomain(),
    name = name,
    color = color,
)

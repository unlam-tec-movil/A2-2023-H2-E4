package ar.edu.unlam.mobile.scaffold.domain.models
data class Category(
    val id: Int,
    val type: TransactionType,
    val name: String,
    val color: ColorsCategory,
)

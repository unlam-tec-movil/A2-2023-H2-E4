package ar.edu.unlam.mobile.scaffold.data.transaction.models

sealed class Screens(val route: String) {
    object ChartScreen : Screens("chartScreen")
    object Category : Screens("CreateCategory")
    object AllCategories : Screens("allCategoriesScreen/{type}") {
        fun createRoute(type: String) = "allCategoriesScreen/$type"
    }
    object AddTransactionScreen : Screens("addTransactionScreen")
}

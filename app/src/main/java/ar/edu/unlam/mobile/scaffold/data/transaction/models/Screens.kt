package ar.edu.unlam.mobile.scaffold.data.transaction.models

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Secondary : Screens("secondary") {
        fun withId(id: Int) = "secondary/$id"
    }
    object ChartScreen : Screens("chartScreen")
    object TransactionScreen : Screens("transactionScreen")
    object Category : Screens("CreateCategory")
    object AllCategories : Screens("allCategoriesScreen/{type}") {
        fun createRoute(type: String) = "allCategoriesScreen/$type"
    }
    object AddTransactionScreen : Screens("addTransactionScreen/{categoryId}") {
        fun categoryRoute(categoryId: Int) = "addTransactionScreen/$categoryId"
    }
}

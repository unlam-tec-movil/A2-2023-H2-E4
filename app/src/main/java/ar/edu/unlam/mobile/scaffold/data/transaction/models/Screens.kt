package ar.edu.unlam.mobile.scaffold.data.transaction.models

sealed class Screens(val route: String) {
    object Home : Screens("home")
    object Secondary : Screens("secondary") {
        fun withId(id: Int) = "secondary/$id"
    }
    object ChartScreen : Screens("chartScreen")
    object Category : Screens("CreateCategory")
    object AddTransactionScreen : Screens("addTransactionScreen")
    object TransactionDisplayerScreen : Screens("transactionDisplayerScreen")
    object TransactionDetailsScreen : Screens("transactionDetailsScreen/{id}") {
        fun withId(id: Int) = "transactionDetailsScreen/$id"
    }
}

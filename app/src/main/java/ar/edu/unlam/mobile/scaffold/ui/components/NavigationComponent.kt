package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Screens
import ar.edu.unlam.mobile.scaffold.ui.screens.addTransactionScreen.AddTransactionScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen.AllCategoriesScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen.CategoryScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen.ChartScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.transactionScreen.TransactionScreen

@Composable
fun NavigationComponent(
    navigationController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navigationController,
        startDestination = Screens.ChartScreen.route,
        modifier = modifier,
    ) {
        composable(Screens.Category.route) {
            CategoryScreen(controller = navigationController)
        }
        composable(Screens.AllCategories.route) { navBackStackEntry ->
            val type = navBackStackEntry.arguments?.getString("type") ?: "Ingresos"
            AllCategoriesScreen(controller = navigationController, type = type)
        }
        composable(Screens.ChartScreen.route) {
            ChartScreen(controller = navigationController)
        }
        composable(Screens.TransactionScreen.route) {
            TransactionScreen(controller = navigationController)
        }
        composable(Screens.AddTransactionScreen.route) {
            AddTransactionScreen(controller = navigationController)
        }
    }
}

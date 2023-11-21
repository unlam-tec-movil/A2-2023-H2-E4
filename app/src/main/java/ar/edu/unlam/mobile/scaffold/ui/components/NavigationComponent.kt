package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Screens
import ar.edu.unlam.mobile.scaffold.ui.screens.addTransactionScreen.AddTransactionScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen.AllCategoriesScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen.CategoryScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen.ChartScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.transactionDetails.TransactionDetailsScreen
import ar.edu.unlam.mobile.scaffold.ui.screens.transactionDetails.TransactionDetailsScreenViewModel
import ar.edu.unlam.mobile.scaffold.ui.screens.transactionDisplayerScreen.TransactionDisplayerScreen

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
            val type = navBackStackEntry.arguments?.getString("type") ?: "Gastos"
            AllCategoriesScreen(controller = navigationController, type = type)
        }
        composable(Screens.ChartScreen.route) {
            ChartScreen(controller = navigationController)
        }
        composable(Screens.AddTransactionScreen.route) {
            AddTransactionScreen(controller = navigationController)
        }
        composable(Screens.TransactionDisplayerScreen.route) {
            TransactionDisplayerScreen(controller = navigationController)
        }
        composable(
            Screens.TransactionDetailsScreen.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType }),
        ) { navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getInt("id") ?: 1
            val viewModel: TransactionDetailsScreenViewModel = hiltViewModel()
            viewModel.setTransactionId(id)
            TransactionDetailsScreen(controller = navigationController, viewModel = viewModel)
        }
    }
}

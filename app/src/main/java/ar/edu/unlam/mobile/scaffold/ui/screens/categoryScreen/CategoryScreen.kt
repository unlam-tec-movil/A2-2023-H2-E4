package ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.ui.components.category.CategoryCard

@Composable
fun CategoryScreen(
    controller: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel(),
) {
    CategoryCard(viewModel)
}

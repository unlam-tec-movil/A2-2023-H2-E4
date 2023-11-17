package ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.ui.components.category.AllCategoriesDisplay

@Composable
fun AllCategoriesScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    controller: NavHostController,
    type: String,
) {
    // Cargar las categorías correspondientes al tipo
    LaunchedEffect(key1 = Unit) {
        viewModel.getCategoriesByType(type)
    }

    // Obtener las categorías que deseas mostrar (puedes ajustar esto según tus necesidades)
    val categories: List<Category> by viewModel.categories.collectAsState()

    // Llamar a AllCategoriesDisplay con las categorías obtenidas
    AllCategoriesDisplay(
        categories = categories,
        onSelectable = false,
        controller = controller,
    )
}

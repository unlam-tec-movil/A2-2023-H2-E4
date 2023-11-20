package ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.ui.components.category.AllCategoriesDisplay

@OptIn(ExperimentalMaterial3Api::class)
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        TopAppBar(
            title = { Text(text = "Categorias") },
            navigationIcon = {
                IconButton(onClick = { controller.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            },
        )
        // Llamar a AllCategoriesDisplay con las categorías obtenidas
        AllCategoriesDisplay(
            categories = categories,
            onSelectable = true,
            controller = controller,
        )
    }
}

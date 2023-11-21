package ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.ui.components.category.ColorBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllCategoriesScreen(
    controller: NavHostController,
    type: String,
    viewModel: CategoryViewModel = hiltViewModel(),
) {
    val categories by viewModel.categories.collectAsState()

    LaunchedEffect(type) {
        viewModel.getCategoriesByType(type)
    }

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
        // Muestra la lista de categorías
        LazyColumn {
            items(categories) { category ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ColorBox(
                        color = Color(android.graphics.Color.parseColor(category.color)),
                        isSelected = false,
                    ) {}
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = category.name,
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@file:Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST")

package ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.data.transaction.models.ColorsCategory
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Screens
import ar.edu.unlam.mobile.scaffold.ui.components.category.CategoryColor
import ar.edu.unlam.mobile.scaffold.ui.components.category.CategoryDisplay
import ar.edu.unlam.mobile.scaffold.ui.components.category.CategoryRadioButton

@Composable
fun CategoryScreen(
    controller: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel(),
) {
    var selectedColor by remember { mutableStateOf<ColorsCategory?>(null) }
    var colorClick by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Gastos") }
    var categoryName by remember { mutableStateOf("") }

    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    val categories by viewModel.categories.collectAsState()

    LaunchedEffect(selectedOption) {
        viewModel.getCategoriesByType(selectedOption)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Crear Categorias",
            fontSize = 25.sp,
            modifier = Modifier.padding(16.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = categoryName,
            onValueChange = { categoryName = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            placeholder = {
                Text(
                    text = "Ingrese el nombre de una categoria",
                    fontSize = 15.sp,
                    color = if (!colorClick) Color.Gray else Color.Red,
                )
            },
        )

        Spacer(modifier = Modifier.height(5.dp))

        // Componente RadioButton
        CategoryRadioButton(selectedOption) { updatedOption ->
            selectedOption = updatedOption
        }

        Spacer(modifier = Modifier.height(5.dp))

        // Componente que se encarga de los colores
        CategoryColor { clickedColor ->
            selectedColor = clickedColor
            colorClick = true
        }

        // Validación que habilita el botón
        val isInputValid = categoryName.isNotBlank() && selectedColor != null

        Button(
            enabled = isInputValid,
            onClick = {
                // Llama funcion de viewModel que hace el insert a la BD
                viewModel.addCategory(categoryName, selectedOption, selectedColor?.colorHex ?: ColorsCategory.ROJO.colorHex)
                categoryName = ""
                viewModel.loadCategories() // Cargar categorías filtradas después de agregar
            },
        ) {
            Text(text = "Agregar")
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(text = "Categorias Agregadas")

        CategoryDisplay(
            categories = categories,
            onSelectable = false,
            maxDisplayedCategories = 8,
            moreButtonText = "Mostrar todas",
            onMoreButtonClick = { controller.navigate(Screens.AllCategories.withType(selectedOption)) },
            onCategoryClick = { /* Lógica al hacer clic en una categoría */ },
        )
    }
}

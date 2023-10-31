package ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen

import CategoryTextField
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import ar.edu.unlam.mobile.scaffold.domain.models.ColorsCategory
import ar.edu.unlam.mobile.scaffold.ui.components.category.CategoryColor
import ar.edu.unlam.mobile.scaffold.ui.components.category.CategoryRadioButton

@Composable
fun CategoryScreen(
    controller: NavHostController,
    viewModel: CategoryViewModel = hiltViewModel(),
) {
    var selectedColor by remember { mutableStateOf<ColorsCategory?>(null) }
    var colorClick by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("Ingresos") }
    var category by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Transparent),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Categorías",
                        color = Color.White,
                        fontSize = 25.sp,
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    // Componente TextField
                    CategoryTextField(
                        colorClick = colorClick,
                        category = category,
                        onCategoryChanged = { updatedCategory ->
                            category = updatedCategory
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
                    val isInputValid = category.isNotBlank() && selectedColor != null

                    Button(
                        enabled = isInputValid,
                        onClick = {
                            // Llama funcion de viewModel que hace el insert a la BD
                            viewModel.addCategoryToDatabase(category, selectedOption, selectedColor?.colorHex ?: ColorsCategory.ROJO.colorHex)
                            category = ""
                        },
                    ) {
                        Text(text = "Agregar")
                    }
                }
            }
        }
    }
}

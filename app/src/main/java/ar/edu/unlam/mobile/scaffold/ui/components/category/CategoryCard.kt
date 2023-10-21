package ar.edu.unlam.mobile.scaffold.ui.components.category

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import ar.edu.unlam.mobile.scaffold.data.category.repository.CategoryEntity
import ar.edu.unlam.mobile.scaffold.domain.models.ColorsCategory
import ar.edu.unlam.mobile.scaffold.domain.models.TransactionType
import ar.edu.unlam.mobile.scaffold.ui.screens.categoryScreen.CategoryViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun CategoryCard(viewModel: CategoryViewModel) {
    var selectedColor by remember { mutableStateOf<ColorsCategory?>(null) }
    var category by remember { mutableStateOf("") }
    var isInputValid by remember { mutableStateOf(false) }
    var colorClick by remember { mutableStateOf(false) }

    val bd = viewModel.appDatabase

    // Insertar categorias por defecto usando corutina
    viewModel.viewModelScope.launch(Dispatchers.IO) {
        val defaultCategories = listOf(
            CategoryEntity(0, TransactionType.INCOME, "Salario", ColorsCategory.AMARILLO),
            CategoryEntity(0, TransactionType.INCOME, "Ventas", ColorsCategory.AZUL),
            CategoryEntity(0, TransactionType.EXPENSE, "Alquiler", ColorsCategory.MORADO),
            CategoryEntity(0, TransactionType.EXPENSE, "Comestibles", ColorsCategory.ROJO),
            CategoryEntity(0, TransactionType.EXPENSE, "Transporte", ColorsCategory.AMARILLO),
        )

        defaultCategories.forEach { category ->
            bd.categoryDao().insertCategory(category)
        }
    }

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

                    Row(
                        Modifier.fillMaxWidth(),
                        Arrangement.Center,
                    ) {
                        TextField(
                            value = category,
                            onValueChange = {
                                category = it
                            },
                            placeholder = {
                                Text(
                                    text = "Ingresa una categoria",
                                    fontSize = 15.sp,
                                    color = if (!colorClick) Color.Gray else Color.Red,
                                )
                            },

                            modifier = Modifier
                                .weight(1f)
                                .wrapContentHeight()
                                .padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                ),
                        )
                    }
                    val radioOptions = listOf("Ingresos", "Gastos")
                    var selectedOption by remember {
                        mutableStateOf(radioOptions[0])
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        Modifier.fillMaxWidth(),
                        Arrangement.Center,
                    ) {
                        radioOptions.forEach { option ->
                            val selected = option == selectedOption
                            LabelledRadioButton(
                                label = option,
                                selected = selected,
                                onClick = {
                                    if (!selected) {
                                        selectedOption = option
                                    }
                                },
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(5.dp))

                    NoteColor { clickedColor ->
                        selectedColor = clickedColor
                        colorClick = true
                    }

                    // valida el que no este vacio el TextField y se selecione un color para habilitar el boton
                    if (category.isNotBlank() && selectedColor != null) {
                        isInputValid = true
                    }

                    Button(
                        enabled = isInputValid,
                        onClick = {
                            // Inserta categorías en la base de datos usando una corutina
                            viewModel.viewModelScope.launch(Dispatchers.IO) {
                                val newCategory = CategoryEntity(
                                    id = 0, // Room generará automáticamente un ID único
                                    type = if (selectedOption == "income") {
                                        TransactionType.INCOME
                                    } else {
                                        TransactionType.EXPENSE
                                    },
                                    name = category,
                                    color = getColorCategoryFromHex(selectedColor?.colorHex ?: ColorsCategory.ROJO.colorHex),
                                )

                                bd.categoryDao().insertCategory(newCategory)
                            }
                            category = ""
                            isInputValid = false
                        },
                    ) {
                        Text(text = "Agregar")
                    }
                }
            }
        }
    }
}

// Funcion que recorre el enum de colores y agrega el color al Box
@Composable
fun NoteColor(onColorClick: (ColorsCategory) -> Unit) {
    var selectedColor by remember { mutableStateOf<ColorsCategory?>(null) }
    var colorText by remember { mutableStateOf("Seleccione un color") }

    Column() {
        Text(
            text = colorText,
            color = Color.White,
            fontSize = 15.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .height(56.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.width(8.dp))
            ColorsCategory.values().forEach { color ->
                ColorBox(Color(android.graphics.Color.parseColor(color.colorHex)), selectedColor == color) {
                    selectedColor = color
                    colorText = "Color seleccionado $color"
                    onColorClick(color)
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}

// Funcion captura el color y asigna el borde al Box clickeado
@Composable
fun ColorBox(color: Color, isSelected: Boolean, onColorClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color)
            .clickable {
                onColorClick()
            }
            .border(
                width = 2.dp, // Ancho del borde
                color = if (isSelected) Color.White else Color.Transparent, // Color del borde
                shape = RoundedCornerShape(20.dp),
            ),
    )
}

// Funcion que parcea de hexa a enum para guardar en la bd
fun getColorCategoryFromHex(hexColor: String): ColorsCategory {
    return when (hexColor) {
        ColorsCategory.ROJO.colorHex -> ColorsCategory.ROJO
        ColorsCategory.VERDE.colorHex -> ColorsCategory.VERDE
        ColorsCategory.AZUL.colorHex -> ColorsCategory.AZUL
        ColorsCategory.AMARILLO.colorHex -> ColorsCategory.AMARILLO
        ColorsCategory.NARANJA.colorHex -> ColorsCategory.NARANJA
        ColorsCategory.MORADO.colorHex -> ColorsCategory.MORADO
        else -> ColorsCategory.ROJO // Color predeterminado para la categoria
    }
}

//  Funcion de Radio buttons
@Composable
fun LabelledRadioButton(
    label: String,
    selected: Boolean,
    onClick: (() -> Unit)?,
    enabled: Boolean = true,
    colors: RadioButtonColors = RadioButtonDefaults.colors(),
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            enabled = enabled,
            colors = colors,
        )
        Text(
            text = label,
            color = Color.White,
            fontSize = 15.sp,
            modifier = Modifier.padding(end = 10.dp),
        )
    }
}

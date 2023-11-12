package ar.edu.unlam.mobile.scaffold.ui.components.category

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category

@Composable
fun CategoryDisplay(
    categories: List<Category>,
    selectedCategory: Category?,
    onCategoryClick: (Category) -> Unit,
) {
    // Crear una lista de categorías únicas
    val uniqueCategories = categories.distinctBy { it.name }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        items(uniqueCategories) { uniqueCategory ->
            // Encontrar la categoría correspondiente en la lista original
            val category = categories.find { it.name == uniqueCategory.name }

            // Verificar si la categoría actual es la seleccionada
            val isSelected = category == selectedCategory

            // Mostrar la fila con la caja de color y el nombre de la categoría
            Row(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Spacer(modifier = Modifier.width(8.dp))
                category?.let {
                    CategoryColors(
                        category = it,
                        isSelected = isSelected,
                        onCategoryClick = { onCategoryClick(category) },
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                // Mostrar el nombre de la categoría debajo de la caja de color
                Text(
                    text = uniqueCategory.name, // Ajusta según la estructura de tu Category
                    modifier = Modifier.padding(top = 4.dp),
                    color = if (isSelected) Color.White else Color.Gray,
                )
            }
        }
    }
}

@Composable
fun CategoryColors(
    category: Category,
    isSelected: Boolean,
    onCategoryClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color(android.graphics.Color.parseColor(category.color)))
            .clickable {
                onCategoryClick()
            }
            .border(
                width = 2.dp,
                color = if (isSelected) Color.White else Color.Transparent,
                shape = RoundedCornerShape(20.dp),
            ),
    )
}

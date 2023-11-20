package ar.edu.unlam.mobile.scaffold.ui.components.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category

@Composable
fun AllCategoriesDisplay(
    categories: List<Category>,
    onSelectable: Boolean = true,
    controller: NavHostController,
    onCategoryClick: (Category) -> Unit = {},
) {
    val uniqueCategories = categories.distinctBy { it.name }
    var selectedCategory by remember { mutableStateOf<Category?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        LazyColumn {
            items(uniqueCategories) { uniqueCategory ->
                val isSelected = uniqueCategory == selectedCategory

                val clickableModifier = if (onSelectable) {
                    Modifier.clickable {
                        selectedCategory = uniqueCategory
                        onCategoryClick(uniqueCategory)
                    }
                } else {
                    Modifier
                }

                Row(
                    modifier = clickableModifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CategoryColors(
                        category = uniqueCategory,
                        isSelected = isSelected,
                        onCategoryClick = {
                            selectedCategory = uniqueCategory
                            onCategoryClick(uniqueCategory)
                            controller.navigateUp()
                        },
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "${uniqueCategory.name}",
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = Color.Black,
                    )
                }

                Spacer(modifier = Modifier.height(8.dp)) // Agregar espacio entre las categorías
            }
        }
    }
}

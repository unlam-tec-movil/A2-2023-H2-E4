package ar.edu.unlam.mobile.scaffold.ui.components.category

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType

@Composable
fun CategoryDisplay(
    categories: List<Category>,
    selectedCategory: Category?,
    onCategoryClick: (Category) -> Unit,
    maxDisplayedCategories: Int = Int.MAX_VALUE,
    moreButtonText: String = "Ver más",
    onMoreButtonClick: () -> Unit = {},
) {
    val uniqueCategories = categories.distinctBy { it.name }

    val categoriesToShow = uniqueCategories.take(maxDisplayedCategories)

    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            items(categoriesToShow) { uniqueCategory ->
                val category = categories.find { it == uniqueCategory }
                val isSelected = category == selectedCategory

                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                ) {
                    if (category != null) {
                        CategoryColors(
                            category = category,
                            isSelected = isSelected,
                            onCategoryClick = { onCategoryClick(category) },
                        )
                    }
                    Text(
                        text = "${uniqueCategory.name}",
                        color = if (isSelected) Color.Green else Color.Gray,
                    )
                }
            }
        }

        if (uniqueCategories.size >= maxDisplayedCategories) {
            Button(
                onClick = onMoreButtonClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
            ) {
                Text(text = moreButtonText)
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

@Preview(showBackground = true)
@Composable
fun CategoryDisplayPreview() {
    val categories = listOf(
        Category(1, TransactionType.Ingresos, "Category1", "#FF5733"),
        Category(2, TransactionType.Gastos, "Category2", "#33FF57"),
        Category(3, TransactionType.Ingresos, "Category3", "#5733FF"),
        Category(4, TransactionType.Ingresos, "Category4", "#FF5733"),
        Category(5, TransactionType.Gastos, "Category5", "#33FF57"),
        Category(6, TransactionType.Ingresos, "Category6", "#5733FF"),
        Category(7, TransactionType.Ingresos, "Category7", "#5733FF"),
        Category(8, TransactionType.Ingresos, "Category8", "#5733FF"),
        Category(9, TransactionType.Ingresos, "Category9", "#5733FF"),
    )

    CategoryDisplay(
        categories = categories,
        selectedCategory = categories.firstOrNull(),
        maxDisplayedCategories = categories.size,
        moreButtonText = "Mostrar todas",
        onMoreButtonClick = { /* Lógica al hacer clic en "Ver más" */ },
        onCategoryClick = { /* Lógica al hacer clic en una categoría */ },
    )
}

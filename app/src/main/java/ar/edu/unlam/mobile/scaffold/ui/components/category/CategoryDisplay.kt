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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category

@Composable
fun CategoryDisplay(
    categories: List<Category>,
    onSelectable: Boolean = true,
    onCategoryClick: (Category) -> Unit,
    maxDisplayedCategories: Int = Int.MAX_VALUE,
    moreButtonText: String = "Ver más",
    onMoreButtonClick: () -> Unit = {},
    controller: NavHostController,
) {
    val uniqueCategories = categories.distinctBy { it.name }

    var selectedCategory by remember { mutableStateOf<Category?>(null) }

    Column {
        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
        ) {
            items(uniqueCategories.take(maxDisplayedCategories)) { uniqueCategory ->
                val isSelected = uniqueCategory == selectedCategory

                val modifier = if (onSelectable) {
                    Modifier.clickable {
                        selectedCategory = uniqueCategory
                        onCategoryClick(uniqueCategory)
                    }
                } else {
                    Modifier
                }

                Column(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                ) {
                    CategoryColors(
                        category = uniqueCategory,
                        isSelected = isSelected,
                        onCategoryClick = {
                            selectedCategory = uniqueCategory
                            onCategoryClick(uniqueCategory)
                        },
                    )
                    Text(
                        text = "${uniqueCategory.name}",
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = Color.Black,
                    )
                }
            }
        }

        if (uniqueCategories.size > maxDisplayedCategories) {
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
                color = if (isSelected) Color.Black else Color.Transparent,
                shape = RoundedCornerShape(20.dp),
            ),
    )
}

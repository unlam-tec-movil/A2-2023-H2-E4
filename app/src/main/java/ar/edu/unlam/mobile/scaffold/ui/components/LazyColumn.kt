package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffold.data.category.Category

@Composable
fun Statistics(
    column1: String,
    column2: String,
    list: MutableList<Category>,
    color1: Color,
    color2: Color
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .background(color1)
            .padding(0.dp, 10.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = column1, fontSize = 30.sp, color = Color.White)
        Text(text = column2, fontSize = 30.sp, color = Color.White)
    }
    ShowCategories(list, color2)
}

@Composable
fun ShowCategories(data: MutableList<Category>, color: Color) {
    LazyColumn (
        modifier = Modifier.fillMaxWidth()
            .height(190.dp)
            .background(color = color)
    ) {
        items(data) { item -> ListItemRow(item) }
    }
}

@Composable
private fun ListItemRow(item: Category) {
    Row (
        modifier = Modifier.fillMaxWidth()
    ){
        Spacer(modifier = Modifier.width(20.dp))
        Column (
            Modifier
                .padding(5.dp)
                .width(165.dp)
                .fillMaxHeight()
        ){
            Text(item.name, textAlign = TextAlign.Left, fontSize = 20.sp)
        }
        Column (
            Modifier
                .padding(5.dp)
                .width(165.dp)
                .fillMaxHeight()
        ) {
            Text("$${item.totalAmount}", textAlign = TextAlign.Right, fontSize = 20.sp)
        }
    }
}

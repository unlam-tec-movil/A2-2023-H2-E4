package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ar.edu.unlam.mobile.scaffold.data.category.Category

@Composable
fun Statistics(column1: String, column2: String, list: MutableList<Category>) {
    Row (
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = column1, fontSize = 30.sp)
        Text(text = column2, fontSize = 30.sp)
    }
    Spacer(Modifier.size(10.dp))
    ShowCategories(list)
}

@Composable
fun ShowCategories(data: MutableList<Category>) {
    LazyColumn (
        modifier = Modifier.fillMaxWidth(),
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
        ){
            Text(item.name, textAlign = TextAlign.Left, fontSize = 20.sp)
        }
        Column (
            Modifier
                .padding(5.dp)
                .width(165.dp)
        ) {
            Text("$${item.totalAmount}", textAlign = TextAlign.Right, fontSize = 20.sp)
        }
    }
}

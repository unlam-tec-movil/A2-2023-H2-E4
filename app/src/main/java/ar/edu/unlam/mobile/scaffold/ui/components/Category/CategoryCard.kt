@file:Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")

package ar.edu.unlam.mobile.scaffold.ui.components.Category
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.*

@Composable
fun CategoryCard() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Gray),
                contentAlignment = Alignment.Center
            ){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Categorías",
                        color = Color.White,
                        fontSize = 25.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Row(
                        Modifier.fillMaxWidth(), Arrangement.Center
                    ) {
                        var category by remember {
                            mutableStateOf(value="")
                        }
                        TextField(
                            value = category,
                            onValueChange = {
                                category = it
                            },
                            placeholder = { Text("Ingresa una categoria") },
                            modifier = Modifier
                                .weight(1f)
                                .height(40.dp)
                                .padding(
                                    start = 10.dp,
                                    end = 10.dp,
                                )
                                .background(
                                    Color.White,
                                    shape = RoundedCornerShape(10.dp),
                                )
                        )
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(Color.Blue),
                            modifier = Modifier
                                .size(40.dp)
                        ) {
                            Text(
                                text = "+",
                                color = Color.White,
                                fontSize = 20.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
@Preview
fun CategoryCardPreview(){
    CategoryCard()
}
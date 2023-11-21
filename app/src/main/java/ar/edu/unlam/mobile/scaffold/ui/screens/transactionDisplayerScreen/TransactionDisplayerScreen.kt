package ar.edu.unlam.mobile.scaffold.ui.screens.transactionDisplayerScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Screens
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Transaction
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDisplayerScreen(
    controller: NavHostController,
    viewModel: TransactionDisplayerViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Transacciones",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            },
        )
        when (viewModel.screenState.value) {
            is TransactionDisplayerScreenUIState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }

            is TransactionDisplayerScreenUIState.Error -> {
                Text("Error: ${(viewModel.screenState.value as TransactionDisplayerScreenUIState.Error).message}")
            }
            is TransactionDisplayerScreenUIState.Success -> {
                val transactions = (viewModel.screenState.value as TransactionDisplayerScreenUIState.Success).transactions
                LazyColumn {
                    items(transactions.size) {
                        ListItem(
                            headlineContent = {
                                Column {
                                    Text(
                                        text = "${transactions[it].date}",
                                        textAlign = TextAlign.Left,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Light,
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                    )
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth(),
                                    ) {
                                        Text(
                                            text = transactions[it].description,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier
                                                .width(180.dp)
                                                .padding(5.dp),
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        var typeText = ""
                                        var colorText: Color = Color.Black

                                        if (transactions[it].type == TransactionType.Ingresos) {
                                            typeText = String.format("+$ %.2f", transactions[it].amount)
                                            colorText = Color.Green
                                        } else {
                                            typeText = String.format("-$ %.2f", transactions[it].amount)
                                            colorText = Color.Black
                                        }
                                        Text(
                                            text = typeText,
                                            textAlign = TextAlign.Right,
                                            fontWeight = FontWeight.Bold,
                                            color = colorText,
                                            modifier = Modifier
                                                .width(120.dp)
                                                .padding(5.dp),
                                            maxLines = 1,
                                        )
                                    }
                                }
                            },
                            trailingContent = {
                                IconButton(onClick = {
                                    controller.navigate(
                                        Screens.TransactionDetailsScreen.withId(
                                            transactions[it].id,
                                        ),
                                    )
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.KeyboardArrowRight,
                                        contentDescription = "Details",
                                    )
                                }
                            },
                            shadowElevation = 3.dp,
                        )
                    }
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }
}

@Composable
fun CustomLazyColumn(items: List<Transaction>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer),

    ) {
        Text(
            text = "Descripcion",
            modifier = Modifier
                .width(200.dp)
                .padding(16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,

        )
        Text(
            text = "Monto",
            modifier = Modifier
                .padding(16.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,

        )
    }
    LazyColumn {
        items(items.size) {
            CustomCell(item = items[it])
        }
    }
}

@Composable
fun CustomCell(item: Transaction) {
    ListItem(
        headlineContent = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),

            ) {
                Text(
                    text = item.description,
                    modifier = Modifier
                        .width(180.dp)
                        .padding(10.dp),
                )
                Text(
                    text = String.format("%.2f", item.amount),
                    modifier = Modifier
                        .width(120.dp)
                        .padding(10.dp),
                )
            }
        },
        trailingContent = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Details",
                )
            }
        },

    )
}

package ar.edu.unlam.mobile.scaffold.ui.screens.transactionDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType
import ar.edu.unlam.mobile.scaffold.ui.screens.transactionDisplayerScreen.TransactionDisplayerScreenUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDetailsScreen(
    controller: NavHostController,
    viewModel: TransactionDetailsScreenViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        TopAppBar(
            title = {
                Text(
                    text = "Detalles de transaccion",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            },
            navigationIcon = {
                IconButton(onClick = { controller.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            },

        )
        when (viewModel.screenState.value) {
            is TransactionDetailsScreenUIState.Error -> {
                Text("Error: ${(viewModel.screenState.value as TransactionDisplayerScreenUIState.Error).message}")
            }
            TransactionDetailsScreenUIState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }
            is TransactionDetailsScreenUIState.Success -> {
                val transaction = (viewModel.screenState.value as TransactionDetailsScreenUIState.Success).transaction
                if (transaction != null) {
                    Column(
                        modifier = Modifier
                            .padding(10.dp),
                    ) {
                        Text(
                            text = "N° de operacion: ${transaction.id}",
                            textAlign = TextAlign.Left,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.inverseOnSurface),
                        )
                        Text(
                            text = "Realizada el: ${transaction.date}",
                            textAlign = TextAlign.Left,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.inverseOnSurface),
                        )

                        Text(
                            text = "Moneda",
                            textAlign = TextAlign.Left,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .padding(top = 10.dp),
                        )

                        Text(text = transaction.currency.description)

                        Text(
                            text = "Descripcion",
                            textAlign = TextAlign.Left,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .padding(top = 10.dp),
                        )
                        Text(text = transaction.description)

                        Text(
                            text = "Categoria",
                            textAlign = TextAlign.Left,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .padding(top = 10.dp),
                        )
                        Text(text = transaction.category.name)

                        Text(
                            text = "Tipo",
                            textAlign = TextAlign.Left,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Light,
                            modifier = Modifier
                                .padding(top = 10.dp),
                        )
                        Text(text = transaction.type.toString())
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.inverseOnSurface)
                                .padding(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp),
                        ) {
                            var typeText = ""
                            typeText = if (transaction.type == TransactionType.Ingresos) {
                                "Recibiste"
                            } else {
                                "Total"
                            }

                            Text(
                                text = "$typeText",
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Light,
                                modifier = Modifier,
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Text(
                                text = String.format("$%.2f", transaction.amount),
                                modifier = Modifier,
                                textAlign = TextAlign.Center,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                    }
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.loadData()
    }
}

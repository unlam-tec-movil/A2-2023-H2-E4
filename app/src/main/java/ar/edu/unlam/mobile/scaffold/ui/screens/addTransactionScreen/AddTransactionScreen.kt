package ar.edu.unlam.mobile.scaffold.ui.screens.addTransactionScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Screens
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType
import ar.edu.unlam.mobile.scaffold.ui.components.category.CategoryDisplay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    controller: NavHostController,
    viewModel: AddTransactionScreenViewModel = hiltViewModel(),
) {
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        TopAppBar(
            title = { Text(text = "Agregar Transaccion") },
            navigationIcon = {
                IconButton(onClick = { controller.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                    )
                }
            },
        )
        TabRow(
            selectedTabIndex = viewModel.selectedTab.value.ordinal,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ) {
            viewModel.tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title.name) },
                    selected = viewModel.selectedTab.value.ordinal == index,
                    onClick = {
                        viewModel.setTab(TransactionType.valueOf(title.toString()))
                    },
                )
            }
        }

        when (viewModel.transactionScreenUIState.value) {
            is TransactionScreenUIState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }

            is TransactionScreenUIState.Success -> {
                // Muestra el contenido principal utilizando viewModel.transactionScreenUIState.value
                val categories =
                    (viewModel.transactionScreenUIState.value as TransactionScreenUIState.Success).categories
                val currencies =
                    (viewModel.transactionScreenUIState.value as TransactionScreenUIState.Success).currencies
                val selectedCurrency =
                    (viewModel.transactionScreenUIState.value as TransactionScreenUIState.Success).selectedCurrency
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                ) {
                    TextField(
                        value = viewModel.amount.value,
                        onValueChange = { viewModel.setAmount(it) },
                        placeholder = { Text("Ingresa un monto") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Number,
                        ),
                        modifier = Modifier
                            .width(200.dp),
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = !expanded
                        },
                    ) {
                        TextField(
                            value = (viewModel.selectedCurrency.value?.code ?: ""),
                            onValueChange = { },
                            readOnly = true,
                            modifier = Modifier
                                .menuAnchor()
                                .width(100.dp),
                        )
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                        ) {
                            currencies.forEach { currency ->
                                DropdownMenuItem(
                                    text = { Text(text = currency.code) },
                                    onClick = {
                                        viewModel.setSelectedCurrency(currency)
                                        expanded = false
//                                            Toast.makeText(context, currency.code, Toast.LENGTH_SHORT).show()
                                    },
                                )
                            }
                        }
                    }
                }

                Text(text = "Categorías")
                CategoryDisplay(
                    categories = categories,
                    onSelectable = true,
                    onCategoryClick = { viewModel.setSelectedCategory(it) },
                    maxDisplayedCategories = 8,
                    moreButtonText = "Mostrar más",
                    onMoreButtonClick = {
                        controller.navigate(Screens.AllCategories.createRoute(viewModel.selectedTab.value.toString()))
                    },
                    controller = controller,
                )

                Text(text = "Comentario")
                TextField(
                    value = viewModel.comment.value,
                    onValueChange = { viewModel.setComment(it) },
                    placeholder = { Text("Ingresa un comentario") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 55.dp,
                            end = 55.dp,
                            top = 20.dp,
                        ),
                )
                Spacer(modifier = Modifier.weight(1f)) // Esto asegura que el botón siempre esté en la parte inferior
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = {
                        if (viewModel.amount.value == "") {
                            return@Button
                        }
                        viewModel.setConvertedValue(viewModel.amount.value)
                        viewModel.insertTransaction()
                    },
                    enabled = viewModel.isButtonEnabled.value, // Habilita o deshabilita el botón según el estado
                ) {
                    Text(text = "Agregar")
                }
            }

            is TransactionScreenUIState.Error -> {
                Text("Error: ${(viewModel.transactionScreenUIState.value as TransactionScreenUIState.Error).message}")
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.loadData()
    }
}

/*@Composable
fun ContentScreen(
    categories: List<Category>,
    currencies: List<Currency>,
    selectedCurrency: Currency?,
) {
    Text(text = "Categorías")
    CategoryDisplay(
        categories = categories,
        onSelectable = true,
        onCategoryClick = { },
        maxDisplayedCategories = 8,
        moreButtonText = "Mostrar más",
        onMoreButtonClick = { },
        controller = controller,
    )
}
*/

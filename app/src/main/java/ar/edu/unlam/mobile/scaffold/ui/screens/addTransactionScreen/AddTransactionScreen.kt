package ar.edu.unlam.mobile.scaffold.ui.screens.addTransactionScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    OnselectedCategory: String,
) {
    var expanded by remember { mutableStateOf(false) }
    var id_category by remember { mutableStateOf(0) }
    if (OnselectedCategory != "categoryId") {
        id_category = OnselectedCategory.toIntOrNull() ?: 0
    }

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
        Text(text = "$id_category")
        when (val screenState = viewModel.transactionScreenUIState.value) {
            is TransactionScreenUIState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterHorizontally),
                )
            }

            is TransactionScreenUIState.Success -> {
                val categories = screenState.categories
                val currencies = screenState.currencies
                val selectedCurrency = screenState.selectedCurrency
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                ) {
                    TextField(
                        value = viewModel.amount.value,
                        onValueChange = {
                            val filteredValue = it.filter { char -> char.isDigit() }
                            viewModel.setAmount(filteredValue)
                        },
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
                    onCategoryClick = { selectedCategory ->
                        viewModel.setSelectedCategory(selectedCategory)
                    },
                    maxDisplayedCategories = 8,
                    moreButtonText = "Mostrar más",
                    onMoreButtonClick = {
                        // viewModel.loadCategories()
                        controller.navigate(Screens.AllCategories.createRoute(viewModel.selectedTab.value.toString()))
                    },
                    controller = controller,
                )

                Text(text = "Comentario")
                TextField(
                    value = viewModel.comment.value,
                    onValueChange = {
                        val filteredValue = it.filter { char -> char.isDigit() }
                        viewModel.setComment(filteredValue)
                    },
                    placeholder = { Text("Ingresa un comentario") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 55.dp,
                            end = 55.dp,
                            top = 20.dp,
                        ),
                )
            }

            is TransactionScreenUIState.Error -> {
                Text("Error: ${screenState.message}")
            }
        }
        Spacer(modifier = Modifier.weight(1f)) // Esto asegura que el botón siempre esté en la parte inferior

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .imePadding(),
            onClick = {
                if (viewModel.amount.value == "") {
                    return@Button
                }
                viewModel.setConvertedValue(viewModel.amount.value)
                viewModel.insertTransaction()
            },
            enabled = viewModel.isButtonEnabled.value,
        ) {
            when (viewModel.transactionButtonState.value) {
                TransactionButtonState.Finished -> {
                    Text(
                        text = "Agregar",
                        modifier = Modifier
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp,
                    )
                }
                TransactionButtonState.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(15.dp),
                        color = Color.White,
                    )
                }
            }
        }
    }

    LaunchedEffect(Unit, id_category) {
        viewModel.loadData()
        viewModel.getCategoriesById(id_category)
    }
}

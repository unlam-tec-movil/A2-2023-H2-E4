package ar.edu.unlam.mobile.scaffold.ui.screens.transactionScreen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    controller: NavHostController,
    viewModel: TransactionScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var selectedTab by remember { mutableStateOf(0) }
    var selectedCurrency by remember { mutableStateOf("ARS") }
    val tabs = listOf("Expense", "Income")
    //val selectedTabState by viewModel.selectedTab.collectAsState()
    val convertedValue by viewModel.convertedValue.collectAsState()
    var amount by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val localfocusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .clickable {
                localfocusManager.clearFocus()
            },
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
            selectedTabIndex = selectedTab,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = selectedTab == index,
                    onClick = {
                        selectedTab = index
                        //viewModel.changeTab(TransactionType.values()[index])
                    },
                )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
        ) {
            TextField(
                value = amount,
                onValueChange = { amount = it },
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
                    value = selectedCurrency,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .width(100.dp),
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    viewModel.currencies.value.forEach { currency ->
                        DropdownMenuItem(
                            text = { Text(text = currency.code) },
                            onClick = {
                                selectedCurrency = currency.code
                                expanded = false
                                Toast.makeText(context, currency.code, Toast.LENGTH_SHORT).show()
                            },
                        )
                    }
                }
            }
        }

        Text(text = convertedValue)

        //Text(text = "Estoy en la pantalla $selectedTabState")
        Button(onClick = {
            viewModel.getCurrencyConversion(
                source = "$selectedCurrency",
                target = "ARS",
                quantity = amount,
            )
        }) {
            Text(text = "Convertir")
        }
        Spacer(modifier = Modifier.weight(1f)) // Esto asegura que el botón siempre esté en la parte inferior

        Button(
            onClick = {
                // Lógica del botón
            },
            modifier = Modifier
                .fillMaxWidth() // El botón ocupará todo el ancho
                .padding(16.dp), // Agrega un espacio alrededor del botón

        ) {
            Text(text = "Agregar")
        }
    }
}

@Composable
@Preview
fun TransactionScreenPreview() {
    TransactionScreen(controller = rememberNavController())
}

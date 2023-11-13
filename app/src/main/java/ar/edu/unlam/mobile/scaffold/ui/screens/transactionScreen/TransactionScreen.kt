package ar.edu.unlam.mobile.scaffold.ui.screens.transactionScreen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.mutableIntStateOf
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
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Category
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Currency
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType
import ar.edu.unlam.mobile.scaffold.ui.components.category.CategoryDisplay
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    controller: NavHostController,
    viewModel: TransactionScreenViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    var selectedTab by remember { mutableIntStateOf(0) }
    var selectedCurrency by remember { mutableStateOf(Currency(0, "ARS", "Argentine Peso")) }
    var selectedCategory by remember { mutableStateOf(Category(0, TransactionType.Ingresos, "Comida", "FFFFFF")) }
    val categories by viewModel.categories.collectAsState()
//    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    val tabs = TransactionType.values().toList()
    val selectedTabState by viewModel.selectedTab.collectAsState()
    val convertedValue by viewModel.convertedValue.collectAsState()
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
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
                    text = { Text(title.name) },
                    selected = selectedTab == index,
                    onClick = {
                        selectedTab = index
                        viewModel.changeTab(TransactionType.valueOf(title.toString()))
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
                    value = selectedCurrency.code,
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
                                selectedCurrency = currency
                                expanded = false
                                Toast.makeText(context, currency.code, Toast.LENGTH_SHORT).show()
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
            maxDisplayedCategories = 8,
            moreButtonText = "Mostrar más",
            onMoreButtonClick = { /* Lógica al hacer clic en "Ver más" */ },
            onCategoryClick = { selectedCategory = it },
        )

        Text(text = "Comentario")
        TextField(
            value = description,
            onValueChange = { description = it },
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
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                val current = LocalDateTime.now().format(formatter)
                viewModel.createNewTransaction(selectedTabState, selectedCategory, selectedCurrency, amount.toDouble(), current, description)
                amount = ""
                description = ""
            },
        ) {
            Text(text = "Agregar")
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview
fun TransactionScreenPreview() {
    TransactionScreen(controller = rememberNavController())
}

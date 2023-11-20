package ar.edu.unlam.mobile.scaffold.ui.screens.chartScreen

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
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffold.data.transaction.local.defaultMonth
import ar.edu.unlam.mobile.scaffold.data.transaction.local.defaultYears
import ar.edu.unlam.mobile.scaffold.data.transaction.models.Screens
import ar.edu.unlam.mobile.scaffold.ui.components.PieChart
import ar.edu.unlam.mobile.scaffold.ui.components.Statistics
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChartScreen(
    controller: NavHostController,
    viewModel: ChartScreenViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize(),
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Tus gastos",

                    fontWeight = FontWeight.Bold,
                )
            },
        )
        Body(viewModel = viewModel, controller = controller)
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Body(
    viewModel: ChartScreenViewModel,
    controller: NavHostController,
) {
    val listPieChartInput by viewModel.pieCharInputList.collectAsState()
    var expandedFilterYear by remember { mutableStateOf(false) }
    var expandedFilterMonth by remember { mutableStateOf(false) }
    var selectedYear by remember { mutableStateOf(LocalDate.now().year.toString()) }
    var selectedMonth by remember { mutableStateOf("Mes") }

    LaunchedEffect(viewModel, selectedYear,selectedMonth) {
        if(selectedMonth == "Mes")
            viewModel.loadTransactionForYear(selectedYear)
    }
    LaunchedEffect(viewModel, selectedYear, selectedMonth) {
        if (selectedMonth != "Mes") {
            viewModel.loadTransactionForYearAndMonth(selectedYear, selectedMonth)
        }
    }

    Column {
        Column(
            modifier = Modifier
                .height(300.dp)
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Center,
        ) {
            PieChart(
                data = listPieChartInput,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ExposedDropdownMenuBox(
                expanded = expandedFilterYear,
                onExpandedChange = {
                    expandedFilterYear = !expandedFilterYear
                },
            ) {
                TextField(
                    value = selectedYear,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .width(100.dp)
                )

                ExposedDropdownMenu(
                    expanded = expandedFilterYear,
                    onDismissRequest = { expandedFilterYear = false },
                ) {
                    defaultYears.forEach { year ->
                        DropdownMenuItem(
                            text = { Text(text = year) },
                            onClick = {
                                selectedYear = year
                                expandedFilterYear = false
                            },
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.padding(5.dp))
            ExposedDropdownMenuBox(
                expanded = expandedFilterMonth,
                onExpandedChange = {
                    expandedFilterMonth = !expandedFilterMonth
                },
            ) {
                TextField(
                    value = selectedMonth,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .menuAnchor()
                        .width(150.dp),
                    )

                ExposedDropdownMenu(
                    expanded = expandedFilterMonth,
                    onDismissRequest = { expandedFilterMonth = false },
                ) {
                    defaultMonth.forEach { month ->
                        DropdownMenuItem(
                            text = { Text(text = month.key) },
                            onClick = {
                                selectedMonth = month.value
                                expandedFilterMonth = false
                            },
                        )
                    }
                }
            }

            Box(modifier = Modifier.fillMaxWidth()) {
                FloatingActionButton(
                    modifier = Modifier
                        .padding(all = 5.dp)
                        .align(alignment = Alignment.CenterEnd),
                    shape = MaterialTheme.shapes.medium,
                    onClick = {
                        controller.navigate(Screens.AddTransactionScreen.route)
                    },
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
                }
            }
        }

        Statistics(list = listPieChartInput, viewModel)
    }
}

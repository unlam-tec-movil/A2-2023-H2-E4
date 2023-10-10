package ar.edu.unlam.mobile.scaffold.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ar.edu.unlam.mobile.scaffold.domain.models.Screens

@Composable
fun BottomBar(controller: NavHostController) {
    val navBackStackEntry by controller.currentBackStackEntryAsState()
    NavigationBar {
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == Screens.Home.route } == true,
            onClick = { controller.navigate(Screens.Home.route) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Home",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
            label = { Text(text = "Nuevo Movimiento") },
        )
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == Screens.Secondary.route } == true,
            onClick = { controller.navigate(Screens.Secondary.withId(667867895)) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "SecondaryScreen",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
            label = { Text(text = "Mis Gastos") },
        )
        NavigationBarItem(
            selected = navBackStackEntry?.destination?.hierarchy?.any { it.route == "chart" } == true,
            onClick = { controller.navigate("chart") },
            icon = {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "ChartScreen",
                    tint = MaterialTheme.colorScheme.primary,
                )
            },
            label = { Text(text = "Mis Gastos") }
        )
    }
}

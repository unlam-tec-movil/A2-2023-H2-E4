package ar.edu.unlam.mobile.scaffold.ui.screens.mainScreen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import ar.edu.unlam.mobile.scaffold.ui.components.BottomBar
import ar.edu.unlam.mobile.scaffold.ui.components.NavigationComponent

@Composable
fun MainScreen() {
    // Controller es el elemento que nos permite navegar entre pantallas. Tiene las acciones
    // para navegar como navigate y también la información de en dónde se "encuentra" el usuario
    // a través del back stack
    val controller = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(controller = controller) },
    ) { paddingValue ->
        NavigationComponent(
            navigationController = controller,
            modifier = Modifier.padding(paddingValue),
        )
    }
}

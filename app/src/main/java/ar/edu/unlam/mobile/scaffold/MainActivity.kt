package ar.edu.unlam.mobile.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ar.edu.unlam.mobile.scaffold.data.app.local.PreferenceUtils
import ar.edu.unlam.mobile.scaffold.data.app.local.core.AppDatabase
import ar.edu.unlam.mobile.scaffold.data.app.local.core.category.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.app.local.core.color.ColorEntity
import ar.edu.unlam.mobile.scaffold.data.app.local.core.transactionType.TransactionTypeEntity
import ar.edu.unlam.mobile.scaffold.ui.screens.mainScreen.MainScreen
import ar.edu.unlam.mobile.scaffold.ui.theme.MyApplicationTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appDatabase: AppDatabase // Inyecta la instancia de AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // utilizacion de ShadersPreference para ingresar las categorias
        val hasDefaultCategories = PreferenceUtils.hasDefaultCategories(this)

        if (!hasDefaultCategories) {
            val viewModelScope = CoroutineScope(Dispatchers.IO)

            viewModelScope.launch {
                val defaultColor = listOf(
                    ColorEntity(0, "ROJO", "#ff6961"),
                    ColorEntity(0, "VERDE", "#37bc3d"),
                    ColorEntity(0, "AZUL", "#3777bc"),
                    ColorEntity(0, "AMARILLO", "#FFD700"),
                    ColorEntity(0, "NARANJA", "#ff9d00"),
                    ColorEntity(0, "MORADO", "#a13ed6"),
                )
                defaultColor.forEach { color ->
                    appDatabase.colorDao().insertColor(color)
                }
                val defaultTransactionType = listOf(
                    TransactionTypeEntity(0, "Income"),
                    TransactionTypeEntity(0, "Expense"),
                )
                defaultTransactionType.forEach { transactionType ->
                    appDatabase.transactionTypeDao().insertTransactionType(transactionType)
                }
                val defaultCategories = listOf(
                    CategoryEntity(0, 0, "Salario", 0),
                    CategoryEntity(0, 0, "Ventas", 1),
                    CategoryEntity(0, 1, "Alquiler", 2),
                    CategoryEntity(0, 1, "Comestibles", 3),
                    CategoryEntity(0, 1, "Transporte", 4),
                )
                defaultCategories.forEach { category ->
                    appDatabase.categoryDao().insertCategory(category)
                }

                // Marca las categorías por defecto como agregadas
                PreferenceUtils.setDefaultCategoriesFlag(this@MainActivity)
            }
        }
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    MainScreen()
                }
            }
        }
    }
}

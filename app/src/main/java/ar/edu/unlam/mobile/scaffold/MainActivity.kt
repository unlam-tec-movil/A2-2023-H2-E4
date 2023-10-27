package ar.edu.unlam.mobile.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ar.edu.unlam.mobile.scaffold.data.category.PreferenceUtils
import ar.edu.unlam.mobile.scaffold.data.category.repository.AppDatabase
import ar.edu.unlam.mobile.scaffold.data.category.repository.CategoryEntity
import ar.edu.unlam.mobile.scaffold.domain.models.ColorsCategory
import ar.edu.unlam.mobile.scaffold.domain.models.TransactionType
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
                val defaultCategories = listOf(
                    CategoryEntity(0, TransactionType.INCOME, "Salario", ColorsCategory.AMARILLO),
                    CategoryEntity(0, TransactionType.INCOME, "Ventas", ColorsCategory.AZUL),
                    CategoryEntity(0, TransactionType.EXPENSE, "Alquiler", ColorsCategory.MORADO),
                    CategoryEntity(0, TransactionType.EXPENSE, "Comestibles", ColorsCategory.ROJO),
                    CategoryEntity(0, TransactionType.EXPENSE, "Transporte", ColorsCategory.AMARILLO),
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

package ar.edu.unlam.mobile.scaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ar.edu.unlam.mobile.scaffold.data.core.PreferenceUtils
import ar.edu.unlam.mobile.scaffold.data.transaction.local.TransactionDatabase
import ar.edu.unlam.mobile.scaffold.data.transaction.local.defaultCategories
import ar.edu.unlam.mobile.scaffold.data.transaction.local.defaultCurrencies
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
    lateinit var appDatabase: TransactionDatabase // Inyecta la instancia de AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // utilizacion de ShadersPreference para ingresar el preloader
        val hasDefaultCategories = PreferenceUtils.hasDefaultCategories(this)

        if (!hasDefaultCategories) {
            val viewModelScope = CoroutineScope(Dispatchers.IO)

            viewModelScope.launch {
                defaultCategories.forEach { category ->
                    appDatabase.categoryDao().insertCategory(category)
                }
                defaultCurrencies.forEach { currency ->
                    appDatabase.currencyDao().insertCurrency(currency)
                }
                // Marca el preloader como agregado
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

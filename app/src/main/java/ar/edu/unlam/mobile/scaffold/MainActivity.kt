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
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CategoryEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.local.entities.CurrencyEntity
import ar.edu.unlam.mobile.scaffold.data.transaction.models.TransactionType
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
        // utilizacion de ShadersPreference para ingresar las categorias
        val hasDefaultCategories = PreferenceUtils.hasDefaultCategories(this)

        if (!hasDefaultCategories) {
            val viewModelScope = CoroutineScope(Dispatchers.IO)

            viewModelScope.launch {
//                val defaultColor = listOf(
//                    ColorEntity(0, "ROJO", "#ff6961"),
//                    ColorEntity(0, "VERDE", "#37bc3d"),
//                    ColorEntity(0, "AZUL", "#3777bc"),
//                    ColorEntity(0, "AMARILLO", "#FFD700"),
//                    ColorEntity(0, "NARANJA", "#ff9d00"),
//                    ColorEntity(0, "MORADO", "#a13ed6"),
//                )
//                defaultColor.forEach { color ->
//                    appDatabase.colorDao().insertColor(color)
//                }
//                val defaultTransactionType = listOf(
//                    TransactionTypeEntity(0, "Income"),
//                    TransactionTypeEntity(0, "Expense"),
//                )
//                defaultTransactionType.forEach { transactionType ->
//                    appDatabase.transactionTypeDao().insertTransactionType(transactionType)
//                }
                val defaultCategories = listOf(
                    CategoryEntity(0, TransactionType.Ingresos, "Salario", "#FF5733"),
                    CategoryEntity(0, TransactionType.Ingresos, "Ventas", "#33FF57"),
                    CategoryEntity(0, TransactionType.Ingresos, "Bonificaciones", "#FFD700"),
                    CategoryEntity(0, TransactionType.Ingresos, "Inversiones", "#8A2BE2"),
                    CategoryEntity(0, TransactionType.Ingresos, "Regalías", "#FF8C00"),
                    CategoryEntity(0, TransactionType.Ingresos, "Honorarios", "#00CED1"),
                    CategoryEntity(0, TransactionType.Ingresos, "Subvenciones", "#9ACD32"),
                    CategoryEntity(0, TransactionType.Ingresos, "Préstamos", "#4B0082"),
                    CategoryEntity(0, TransactionType.Gastos, "Alquiler", "#5733FF"),
                    CategoryEntity(0, TransactionType.Gastos, "Comestibles", "#FF5733"),
                    CategoryEntity(0, TransactionType.Gastos, "Transporte", "#33FF57"),
                    CategoryEntity(0, TransactionType.Gastos, "Entretenimiento", "#FF1493"),
                    CategoryEntity(0, TransactionType.Gastos, "Facturas", "#1E90FF"),
                    CategoryEntity(0, TransactionType.Gastos, "Salud", "#FF6347"),
                    CategoryEntity(0, TransactionType.Gastos, "Educación", "#9370DB"),
                    CategoryEntity(0, TransactionType.Gastos, "Ropa", "#20B2AA"),
                )
                defaultCategories.forEach { category ->
                    appDatabase.categoryDao().insertCategory(category)
                }
                val defaultCurrencies = listOf(
                    CurrencyEntity(0, "AED", "United Arab Emirates Dirham"),
                    CurrencyEntity(0, "AFN", "Afghan Afghani"),
                    CurrencyEntity(0, "ALL", "Albanian Lek"),
                    CurrencyEntity(0, "AMD", "Armenian Dram"),
                    CurrencyEntity(0, "ANG", "NL Antillean Guilder"),
                    CurrencyEntity(0, "AOA", "Angolan Kwanza"),
                    CurrencyEntity(0, "ARS", "Argentine Peso"),
                    CurrencyEntity(0, "AUD", "Australian Dollar"),
                    CurrencyEntity(0, "AWG", "Aruban Florin"),
                    CurrencyEntity(0, "AZN", "Azerbaijani Manat"),
                    CurrencyEntity(0, "BAM", "Bosnia-Herzegovina Convertible Mark"),
                    CurrencyEntity(0, "BBD", "Barbadian Dollar"),
                    CurrencyEntity(0, "BDT", "Bangladeshi Taka"),
                    CurrencyEntity(0, "BGN", "Bulgarian Lev"),
                    CurrencyEntity(0, "BHD", "Bahraini Dinar"),
                    CurrencyEntity(0, "BIF", "Burundian Franc"),
                    CurrencyEntity(0, "BMD", "Bermudan Dollar"),
                    CurrencyEntity(0, "BND", "Brunei Dollar"),
                    CurrencyEntity(0, "BOB", "Bolivian Boliviano"),
                    CurrencyEntity(0, "BRL", "Brazilian Real"),
                    CurrencyEntity(0, "BSD", "Bahamian Dollar"),
                    CurrencyEntity(0, "BTN", "Bhutanese Ngultrum"),
                    CurrencyEntity(0, "BWP", "Botswanan Pula"),
                    CurrencyEntity(0, "BYN", "Belarusian ruble"),
                    CurrencyEntity(0, "BYR", "Belarusian Ruble"),
                    CurrencyEntity(0, "BZD", "Belize Dollar"),
                    CurrencyEntity(0, "CAD", "Canadian Dollar"),
                    CurrencyEntity(0, "CDF", "Congolese Franc"),
                    CurrencyEntity(0, "CHF", "Swiss Franc"),
                    CurrencyEntity(0, "CLF", "Unidad de Fomento"),
                    CurrencyEntity(0, "CLP", "Chilean Peso"),
                    CurrencyEntity(0, "CNY", "Chinese Yuan"),
                    CurrencyEntity(0, "COP", "Coombian Peso"),
                    CurrencyEntity(0, "CRC", "Costa Rican Colón"),
                    CurrencyEntity(0, "CUC", "Cuban Convertible Peso"),
                    CurrencyEntity(0, "CUP", "Cuban Peso"),
                    CurrencyEntity(0, "CVE", "Cape Verdean Escudo"),
                    CurrencyEntity(0, "CZK", "Czech Republic Koruna"),
                    CurrencyEntity(0, "DJF", "Djiboutian Franc"),
                    CurrencyEntity(0, "DKK", "Danish Krone"),
                    CurrencyEntity(0, "DOP", "Dominican Peso"),
                    CurrencyEntity(0, "DZD", "Algerian Dinar"),
                    CurrencyEntity(0, "EGP", "Egyptian Pound"),
                    CurrencyEntity(0, "ERN", "Eritrean Nakfa"),
                    CurrencyEntity(0, "ETB", "Ethiopian Birr"),
                    CurrencyEntity(0, "EUR", "Euro"),
                    CurrencyEntity(0, "FJD", "Fijian Dollar"),
                    CurrencyEntity(0, "FKP", "Falkland Islands Pound"),
                    CurrencyEntity(0, "GBP", "British Pound Sterling"),
                    CurrencyEntity(0, "GEL", "Georgian Lari"),
                    CurrencyEntity(0, "GGP", "Guernsey pound"),
                    CurrencyEntity(0, "GHS", "Ghanaian Cedi"),
                    CurrencyEntity(0, "GIP", "Gibraltar Pound"),
                    CurrencyEntity(0, "GMD", "Gambian Dalasi"),
                    CurrencyEntity(0, "GNF", "Guinean Franc"),
                    CurrencyEntity(0, "GTQ", "Guatemalan Quetzal"),
                    CurrencyEntity(0, "GYD", "Guyanaese Dollar"),
                    CurrencyEntity(0, "HKD", "Hong Kong Dollar"),
                    CurrencyEntity(0, "HNL", "Honduran Lempira"),
                    CurrencyEntity(0, "HRK", "Croatian Kuna"),
                    CurrencyEntity(0, "HTG", "Haitian Gourde"),
                    CurrencyEntity(0, "HUF", "Hungarian Forint"),
                    CurrencyEntity(0, "IDR", "Indonesian Rupiah"),
                    CurrencyEntity(0, "ILS", "Israeli New Sheqel"),
                    CurrencyEntity(0, "IMP", "Manx pound"),
                    CurrencyEntity(0, "INR", "Indian Rupee"),
                    CurrencyEntity(0, "IQD", "Iraqi Dinar"),
                    CurrencyEntity(0, "IRR", "Iranian Rial"),
                    CurrencyEntity(0, "ISK", "Icelandic Króna"),
                    CurrencyEntity(0, "JEP", "Jersey pound"),
                    CurrencyEntity(0, "JMD", "Jamaican Dollar"),
                    CurrencyEntity(0, "JOD", "Jordanian Dinar"),
                    CurrencyEntity(0, "JPY", "Japanese Yen"),
                    CurrencyEntity(0, "KES", "Kenyan Shilling"),
                    CurrencyEntity(0, "KGS", "Kyrgystani Som"),
                    CurrencyEntity(0, "KHR", "Cambodian Riel"),
                    CurrencyEntity(0, "KMF", "Comorian Franc"),
                    CurrencyEntity(0, "KPW", "North Korean Won"),
                    CurrencyEntity(0, "KRW", "South Korean Won"),
                    CurrencyEntity(0, "KWD", "Kuwaiti Dinar"),
                    CurrencyEntity(0, "KYD", "Cayman Islands Dollar"),
                    CurrencyEntity(0, "KZT", "Kazakhstani Tenge"),
                    CurrencyEntity(0, "LAK", "Laotian Kip"),
                    CurrencyEntity(0, "LBP", "Lebanese Pound"),
                    CurrencyEntity(0, "LKR", "Sri Lankan Rupee"),
                    CurrencyEntity(0, "LRD", "Liberian Dollar"),
                    CurrencyEntity(0, "LSL", "Lesotho Loti"),
                    CurrencyEntity(0, "LTL", "Lithuanian Litas"),
                    CurrencyEntity(0, "LVL", "Latvian Lats"),
                    CurrencyEntity(0, "LYD", "Libyan Dinar"),
                    CurrencyEntity(0, "MAD", "Moroccan Dirham"),
                    CurrencyEntity(0, "MDL", "Moldovan Leu"),
                    CurrencyEntity(0, "MGA", "Malagasy Ariary"),
                    CurrencyEntity(0, "MKD", "Macedonian Denar"),
                    CurrencyEntity(0, "MMK", "Myanma Kyat"),
                    CurrencyEntity(0, "MNT", "Mongolian Tugrik"),
                    CurrencyEntity(0, "MOP", "Macanese Pataca"),
                    CurrencyEntity(0, "MRO", "Mauritanian ouguiya"),
                    CurrencyEntity(0, "MUR", "Mauritian Rupee"),
                    CurrencyEntity(0, "MVR", "Maldivian Rufiyaa"),
                    CurrencyEntity(0, "MWK", "Malawian Kwacha"),
                    CurrencyEntity(0, "MXN", "Mexican Peso"),
                    CurrencyEntity(0, "MYR", "Malaysian Ringgit"),
                    CurrencyEntity(0, "MZN", "Mozambican Metical"),
                    CurrencyEntity(0, "NAD", "Namibian Dollar"),
                    CurrencyEntity(0, "NGN", "Nigerian Naira"),
                    CurrencyEntity(0, "NIO", "Nicaraguan Córdoba"),
                    CurrencyEntity(0, "NOK", "Norwegian Krone"),
                    CurrencyEntity(0, "NPR", "Nepalese Rupee"),
                    CurrencyEntity(0, "NZD", "New Zealand Dollar"),
                    CurrencyEntity(0, "OMR", "Omani Rial"),
                    CurrencyEntity(0, "PAB", "Panamanian Balboa"),
                    CurrencyEntity(0, "PEN", "Peruvian Nuevo Sol"),
                    CurrencyEntity(0, "PGK", "Papua New Guinean Kina"),
                    CurrencyEntity(0, "PHP", "Philippine Peso"),
                    CurrencyEntity(0, "PKR", "Pakistani Rupee"),
                    CurrencyEntity(0, "PLN", "Polish Zloty"),
                    CurrencyEntity(0, "PYG", "Paraguayan Guarani"),
                    CurrencyEntity(0, "QAR", "Qatari Rial"),
                    CurrencyEntity(0, "RON", "Romanian Leu"),
                    CurrencyEntity(0, "RSD", "Serbian Dinar"),
                    CurrencyEntity(0, "RUB", "Russian Ruble"),
                    CurrencyEntity(0, "RWF", "Rwandan Franc"),
                    CurrencyEntity(0, "SAR", "Saudi Riyal"),
                    CurrencyEntity(0, "SBD", "Solomon Islands Dollar"),
                    CurrencyEntity(0, "SCR", "Seychellois Rupee"),
                    CurrencyEntity(0, "SDG", "Sudanese Pound"),
                    CurrencyEntity(0, "SEK", "Swedish Krona"),
                    CurrencyEntity(0, "SGD", "Singapore Dollar"),
                    CurrencyEntity(0, "SHP", "Saint Helena Pound"),
                    CurrencyEntity(0, "SLL", "Sierra Leonean Leone"),
                    CurrencyEntity(0, "SOS", "Somali Shilling"),
                    CurrencyEntity(0, "SRD", "Surinamese Dollar"),
                    CurrencyEntity(0, "STD", "São Tomé and Príncipe dobra"),
                    CurrencyEntity(0, "SVC", "Salvadoran Colón"),
                    CurrencyEntity(0, "SYP", "Syrian Pound"),
                    CurrencyEntity(0, "SZL", "Swazi Lilangeni"),
                    CurrencyEntity(0, "THB", "Thai Baht"),
                    CurrencyEntity(0, "TJS", "Tajikistani Somoni"),
                    CurrencyEntity(0, "TMT", "Turkmenistani Manat"),
                    CurrencyEntity(0, "TND", "Tunisian Dinar"),
                    CurrencyEntity(0, "TOP", "Tongan Paʻanga"),
                    CurrencyEntity(0, "TRY", "Turkish Lira"),
                    CurrencyEntity(0, "TTD", "Trinidad and Tobago Dollar"),
                    CurrencyEntity(0, "TWD", "New Taiwan Dollar"),
                    CurrencyEntity(0, "TZS", "Tanzanian Shilling"),
                    CurrencyEntity(0, "UAH", "Ukrainian Hryvnia"),
                    CurrencyEntity(0, "UGX", "Ugandan Shilling"),
                    CurrencyEntity(0, "USD", "US Dollar"),
                    CurrencyEntity(0, "UYU", "Uruguayan Peso"),
                    CurrencyEntity(0, "UZS", "Uzbekistan Som"),
                    CurrencyEntity(0, "VEF", "Venezuelan Bolívar"),
                    CurrencyEntity(0, "VND", "Vietnamese Dong"),
                    CurrencyEntity(0, "VUV", "Vanuatu Vatu"),
                    CurrencyEntity(0, "WST", "Samoan Tala"),
                    CurrencyEntity(0, "XAF", "CFA Franc BEAC"),
                    CurrencyEntity(0, "XAG", "Silver Ounce"),
                    CurrencyEntity(0, "XAU", "Gold Ounce"),
                    CurrencyEntity(0, "XCD", "East Caribbean Dollar"),
                    CurrencyEntity(0, "XDR", "Special drawing rights"),
                    CurrencyEntity(0, "XOF", "CFA Franc BCEAO"),
                    CurrencyEntity(0, "XPF", "CFP Franc"),
                    CurrencyEntity(0, "YER", "Yemeni Rial"),
                    CurrencyEntity(0, "ZAR", "South African Rand"),
                    CurrencyEntity(0, "ZMK", "Zambian Kwacha"),
                    CurrencyEntity(0, "ZMW", "Zambian Kwacha"),
                    CurrencyEntity(0, "ZWL", "Zimbabwean dollar"),
                    CurrencyEntity(0, "XPT", "Platinum Ounce"),
                    CurrencyEntity(0, "XPD", "Palladium Ounce"),
                    CurrencyEntity(0, "BTC", "Bitcoin"),
                    CurrencyEntity(0, "ETH", "Ethereum"),
                    CurrencyEntity(0, "BNB", "Binance"),
                    CurrencyEntity(0, "XRP", "Ripple"),
                    CurrencyEntity(0, "SOL", "Solana"),
                    CurrencyEntity(0, "DOT", "Polkadot"),
                    CurrencyEntity(0, "AVAX", "Avalanche"),
                    CurrencyEntity(0, "MATIC", "Matic Token"),
                    CurrencyEntity(0, "LTC", "Litecoin"),
                    CurrencyEntity(0, "ADA", "Cardano"),
                )
                defaultCurrencies.forEach { currency ->
                    appDatabase.currencyDao().insertCurrency(currency)
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

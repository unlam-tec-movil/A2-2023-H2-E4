package ar.edu.unlam.mobile.scaffold.data.category

import android.content.Context
import android.content.SharedPreferences

object PreferenceUtils {
    private const val PREFERENCES_NAME = "MyAppPreferences"

    fun getSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun hasDefaultCategories(context: Context): Boolean {
        return getSharedPreferences(context).getBoolean("hasDefaultCategories", false)
    }

    fun setDefaultCategoriesFlag(context: Context) {
        getSharedPreferences(context).edit().putBoolean("hasDefaultCategories", true).apply()
    }
}

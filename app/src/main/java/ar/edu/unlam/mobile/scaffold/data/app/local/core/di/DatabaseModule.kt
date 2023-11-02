package ar.edu.unlam.mobile.scaffold.data.app.local.core.di

import android.content.Context
import androidx.room.Room
import ar.edu.unlam.mobile.scaffold.data.app.local.core.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // Especifica el alcance del componente donde se proporcionará la dependencia
object DatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
            .build()
    }
}

package ar.edu.unlam.mobile.scaffold.domain.di

import ar.edu.unlam.mobile.scaffold.data.transaction.local.TransactionDatabase
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoCategory
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.CategoryRoomRepository
import ar.edu.unlam.mobile.scaffold.domain.services.CategoryServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {
    @Provides
    fun provideDaoCategory(appDatabase: TransactionDatabase): DaoCategory {
        return appDatabase.categoryDao()
    }

    @Provides
    fun provideCategoryService(categoryRoomRepository: CategoryRoomRepository): CategoryServiceInterface {
        return categoryRoomRepository
    }
}

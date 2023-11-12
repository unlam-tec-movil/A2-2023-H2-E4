package ar.edu.unlam.mobile.scaffold.domain.di

import ar.edu.unlam.mobile.scaffold.data.transaction.local.TransactionDatabase
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoCategory
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.CategoryLocalRepoInterface
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.CategoryRoomRepository
import ar.edu.unlam.mobile.scaffold.data.transaction.repository.CategoryDefaultRepository
import ar.edu.unlam.mobile.scaffold.data.transaction.repository.CategoryRepositoryInterface
import ar.edu.unlam.mobile.scaffold.domain.provider.DefaultDispatcherProvider
import ar.edu.unlam.mobile.scaffold.domain.provider.DispatcherProvider
import ar.edu.unlam.mobile.scaffold.domain.services.CategoryServiceImpl
import ar.edu.unlam.mobile.scaffold.domain.services.CategoryServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CategoryModule {

    @Provides
    fun provideCategoryService(
        categoryRepository: CategoryRepositoryInterface,
        dispatcherProvider: DispatcherProvider,
    ): CategoryServiceInterface {
        return CategoryServiceImpl(categoryRepository, dispatcherProvider)
    }

    @Provides
    fun provideCategoryRepository(categoryLocalRepo: CategoryLocalRepoInterface): CategoryRepositoryInterface {
        return CategoryDefaultRepository(categoryLocalRepo)
    }

    @Provides
    fun provideDaoCategory(appDatabase: TransactionDatabase): DaoCategory {
        return appDatabase.categoryDao()
    }

    @Provides
    fun provideCategoryLocalRepo(daoCategory: DaoCategory): CategoryLocalRepoInterface {
        return CategoryRoomRepository(daoCategory)
    }

    @Provides
    fun provideDispatcherProvider(): DispatcherProvider {
        return DefaultDispatcherProvider()
    }
}

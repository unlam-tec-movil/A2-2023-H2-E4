package ar.edu.unlam.mobile.scaffold.domain.di

import ar.edu.unlam.mobile.scaffold.data.transaction.local.TransactionDatabase
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoTransaction
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.TransactionLocalRepoInterface
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.TransactionRoomRepository
import ar.edu.unlam.mobile.scaffold.data.transaction.repository.TransactionDefaultRepository
import ar.edu.unlam.mobile.scaffold.data.transaction.repository.TransactionRepositoryInterface
import ar.edu.unlam.mobile.scaffold.domain.provider.DispatcherProvider
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionService
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TransactionModule {
    @Provides
    fun provideTransactionService(
        repository: TransactionRepositoryInterface,
        dispatcherProvider: DispatcherProvider,
    ): TransactionServiceInterface {
        return TransactionService(repository, dispatcherProvider)
    }

    @Provides
    fun provideTransactionDefaultRepository(transactionLocalRepo: TransactionLocalRepoInterface): TransactionRepositoryInterface {
        return TransactionDefaultRepository(transactionLocalRepo)
    }

    @Provides
    fun provideTransactionRoomRepository(daoTransaction: DaoTransaction): TransactionLocalRepoInterface {
        return TransactionRoomRepository(daoTransaction)
    }

    @Provides
    fun provideDaoTransaction(appDatabase: TransactionDatabase): DaoTransaction {
        return appDatabase.transactionDao()
    }
}

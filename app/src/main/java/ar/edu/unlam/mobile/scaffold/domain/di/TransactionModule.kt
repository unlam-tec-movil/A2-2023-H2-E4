package ar.edu.unlam.mobile.scaffold.domain.di

import ar.edu.unlam.mobile.scaffold.data.transaction.local.TransactionDatabase
import ar.edu.unlam.mobile.scaffold.data.transaction.local.dao.DaoTransaction
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.TransactionLocalRepoInterface
import ar.edu.unlam.mobile.scaffold.data.transaction.local.repository.TransactionRoomRepository
import ar.edu.unlam.mobile.scaffold.data.transaction.repository.TransactionDefaultRepository
import ar.edu.unlam.mobile.scaffold.data.transaction.repository.TransactionRepositoryInterface
<<<<<<< HEAD
import ar.edu.unlam.mobile.scaffold.domain.provider.DispatcherProvider
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionServiceImpl
=======
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionService
>>>>>>> 0626e11cc1173133f1f2e708654a0ebc0202e5f3
import ar.edu.unlam.mobile.scaffold.domain.services.TransactionServiceInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TransactionModule {
<<<<<<< HEAD
=======
    @Provides
    fun provideTransactionService(repository: TransactionRepositoryInterface): TransactionServiceInterface {
        return TransactionService(repository)
    }

    @Provides
    fun provideTransactionDefaultRepository(transactionLocalRepo: TransactionLocalRepoInterface): TransactionRepositoryInterface {
        return TransactionDefaultRepository(transactionLocalRepo)
    }

    @Provides
    fun provideTransactionRoomRepository(daoTransaction: DaoTransaction): TransactionLocalRepoInterface {
        return TransactionRoomRepository(daoTransaction)
    }
>>>>>>> 0626e11cc1173133f1f2e708654a0ebc0202e5f3

    @Provides
    fun provideDaoTransaction(appDatabase: TransactionDatabase): DaoTransaction {
        return appDatabase.transactionDao()
    }
<<<<<<< HEAD

    @Provides
    fun provideTransactionService(
        transactionRepository: TransactionRepositoryInterface,
        dispatcher: DispatcherProvider
    ): TransactionServiceInterface {
        return TransactionServiceImpl(transactionRepository, dispatcher)
    }

    @Provides
    fun provideTransactionRepository(transactionLocalRepo: TransactionLocalRepoInterface): TransactionRepositoryInterface {
        return TransactionDefaultRepository(transactionLocalRepo)
    }
    @Provides
    fun provideTransactionLocalRepo(daoTransaction: DaoTransaction): TransactionLocalRepoInterface {
        return TransactionRoomRepository(daoTransaction)
    }
=======
>>>>>>> 0626e11cc1173133f1f2e708654a0ebc0202e5f3
}

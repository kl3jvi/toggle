package com.toggle.di

import com.toggle.data.repository.AuthenticationRepositoryImpl
import com.toggle.data.repository.CallHistoryRepositoryImpl
import com.toggle.data.repository.LocalStorageImpl
import com.toggle.data.repository.PeopleRepositoryImpl
import com.toggle.domain.repository.AuthenticationRepository
import com.toggle.domain.repository.CallHistoryRepository
import com.toggle.domain.repository.LocalStorage
import com.toggle.domain.repository.PeopleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindPeopleRepository(repository: PeopleRepositoryImpl): PeopleRepository

    @Binds
    abstract fun bindLocalStorage(storage: LocalStorageImpl): LocalStorage

    @Binds
    abstract fun bindCallHistoryRepositoryRepository(repository: CallHistoryRepositoryImpl): CallHistoryRepository

    @Binds
    abstract fun bindAuthRepositoryRepository(repository: AuthenticationRepositoryImpl): AuthenticationRepository

}
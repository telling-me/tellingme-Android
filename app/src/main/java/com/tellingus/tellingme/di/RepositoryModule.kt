package com.tellingus.tellingme.di

import com.tellingus.tellingme.data.repositoryimpl.DataStoreRepositoryImpl
import com.tellingus.tellingme.data.repositoryimpl.AuthRepositoryImpl
import com.tellingus.tellingme.data.repositoryimpl.NoticeRepositoryImpl
import com.tellingus.tellingme.domain.repository.DataStoreRepository
import com.tellingus.tellingme.domain.repository.AuthRepository
import com.tellingus.tellingme.domain.repository.NoticeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideDataStoreRepository(
        dataStoreRepositoryImpl: DataStoreRepositoryImpl
    ): DataStoreRepository

    @Binds
    @Singleton
    abstract fun provideAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    @Singleton
    abstract fun provideNoticeRepository(noticeRepositoryImpl: NoticeRepositoryImpl): NoticeRepository
}
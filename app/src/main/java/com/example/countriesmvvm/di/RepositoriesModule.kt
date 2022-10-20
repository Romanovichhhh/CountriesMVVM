package com.example.countriesmvvm.di

import com.example.countriesmvvm.data.datasource.local.AppDao
import com.example.countriesmvvm.data.datasource.remote.RemoteDataSourceImpl
import com.example.countriesmvvm.data.datasource.repository.CountryRepositoryImpl
import com.example.countriesmvvm.domain.repository.IContriesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    @Provides
    @Singleton
    fun provideCountriesRepository(appDao: AppDao) : IContriesRepository {
        return CountryRepositoryImpl(RemoteDataSourceImpl(), appDao)
    }
}
package com.example.countriesmvvm.di

import com.example.countriesmvvm.data.datasource.remote.RemoteDataSourceImpl
import com.example.countriesmvvm.data.datasource.repository.CountryRepositoryImpl
import com.example.countriesmvvm.domain.repository.IContriesRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoriesModule {

    fun provideCountriesRepository() : IContriesRepository {
        return CountryRepositoryImpl(RemoteDataSourceImpl())
    }
}
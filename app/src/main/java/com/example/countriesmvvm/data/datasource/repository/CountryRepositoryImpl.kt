package com.example.countriesmvvm.data.datasource.repository

import com.example.countriesmvvm.R
import com.example.countriesmvvm.data.common.ClientResult
import com.example.countriesmvvm.data.common.DataSourceException
import com.example.countriesmvvm.data.datasource.local.AppDao
import com.example.countriesmvvm.data.datasource.mappers.mapToDomainModel
import com.example.countriesmvvm.data.datasource.remote.RemoteDataSource
import com.example.countriesmvvm.domain.models.Continent
import com.example.countriesmvvm.domain.models.Country
import com.example.countriesmvvm.domain.repository.IContriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart


class CountryRepositoryImpl(
    private val apolloClient: RemoteDataSource,
    private val appDao: AppDao
) : IContriesRepository {

    override suspend fun getContinents(): Flow<ClientResult<List<Continent>>> =
        flow {
            when (val result = apolloClient.getContinents()) {
                is ClientResult.Success -> {
                    result.data?.let {
                        it.mapToDomainModel().apply {
                            appDao.saveContinentsList(this)
                            emit(ClientResult.Success(this))
                        }
                    }
                }
                is ClientResult.Error -> {
                    val localResults = appDao.getContinentsList()
                    if (localResults.isNotEmpty()) {
                        emit(ClientResult.Success(localResults))
                    } else {
                        emit(ClientResult.Error(result.exception))
                    }
                }
                else -> {}
            }
        }.onStart { emit(ClientResult.Loading) }

    override suspend fun getCountry(countryCode: String): ClientResult<Country?> {
        return when (val result = apolloClient.getCountry(countryCode)) {
            is ClientResult.Success -> {
                if (result.data != null) {
                    ClientResult.Success(result.data.mapToDomainModel())
                } else {
                    ClientResult.Error(DataSourceException.Unexpected(R.string.unexpected_error_text))
                }
            }
            is ClientResult.Error -> {
                ClientResult.Error(result.exception)
            }
            else -> {
                ClientResult.Error(DataSourceException.Unexpected(R.string.unexpected_error_text))
            }
        }
    }
}

package com.example.countriesmvvm.data.datasource.remote

import com.example.countriesmvvm.ContinentQuery
import com.example.countriesmvvm.ContinentsQuery
import com.example.countriesmvvm.CountryQuery
import com.example.countriesmvvm.R
import com.example.countriesmvvm.data.GraphQLClient
import com.example.countriesmvvm.data.common.ClientResult
import com.example.countriesmvvm.data.common.DataSourceException

class RemoteDataSourceImpl : RemoteDataSource {
    override suspend fun getContinents(): ClientResult<ContinentsQuery.Data?> {
        return try {
            val result = GraphQLClient.getContinents().execute()
            if (result.hasErrors()) {
                ClientResult.Error(DataSourceException.Server(result.errors?.first()))
            } else {
                ClientResult.Success(result.data)
            }
        } catch (e: Exception) {
            ClientResult.Error(DataSourceException.Unexpected(R.string.unexpected_error_text))
        }

    }

    override suspend fun getContinent(code: String): ClientResult<ContinentQuery.Data?> {
        return try {
            val result = GraphQLClient.getContinent(code).execute()
            if (result.hasErrors()) {
                ClientResult.Error(DataSourceException.Server(result.errors?.first()))
            } else {
                ClientResult.Success(result.data)
            }
        } catch (e: Exception) {
            ClientResult.Error(DataSourceException.Unexpected(R.string.unexpected_error_text))
        }
    }

    override suspend fun getCountry(continentCode: String): ClientResult<CountryQuery.Data?> {
        return try {
            val result = GraphQLClient.getCountry(continentCode).execute()
            if (result.hasErrors()) {
                ClientResult.Error(DataSourceException.Server(result.errors?.first()))
            } else {
                ClientResult.Success(result.data)
            }
        } catch (e: Exception) {
            ClientResult.Error(DataSourceException.Unexpected(R.string.unexpected_error_text))
        }
    }
}

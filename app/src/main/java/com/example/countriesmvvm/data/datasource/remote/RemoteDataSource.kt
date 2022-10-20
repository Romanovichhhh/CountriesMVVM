package com.example.countriesmvvm.data.datasource.remote

import com.example.countriesmvvm.ContinentQuery
import com.example.countriesmvvm.ContinentsQuery
import com.example.countriesmvvm.CountryQuery
import com.example.countriesmvvm.data.common.ClientResult

interface RemoteDataSource {
    suspend fun getContinents(): ClientResult<ContinentsQuery.Data?>
    suspend fun getContinent(code: String): ClientResult<ContinentQuery.Data?>
    suspend fun getCountry(continentCode: String): ClientResult<CountryQuery.Data?>
}
package com.example.countriesmvvm.domain.repository

import com.example.countriesmvvm.data.common.ClientResult
import com.example.countriesmvvm.domain.models.Continent
import com.example.countriesmvvm.domain.models.Country
import kotlinx.coroutines.flow.Flow


interface IContriesRepository {
    suspend fun getContinents(): Flow<ClientResult<List<Continent>>>
    suspend fun getCountry(countryCode: String): ClientResult<Country?>
}
package com.example.countriesmvvm.domain.usecases

import com.example.countriesmvvm.data.common.ClientResult
import com.example.countriesmvvm.domain.models.Country
import com.example.countriesmvvm.domain.repository.IContriesRepository
import javax.inject.Inject

class GetCountryUseCase @Inject constructor(
    private val countriesRepository: IContriesRepository
) : UseCase<String, Country?>() {

    override suspend fun call(param: String): ClientResult<Country?> {
        return countriesRepository.getCountry(param)
    }
}
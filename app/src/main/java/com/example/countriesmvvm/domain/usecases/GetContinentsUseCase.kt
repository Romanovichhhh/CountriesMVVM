package com.example.countriesmvvm.domain.usecases

import com.example.countriesmvvm.domain.repository.IContriesRepository
import javax.inject.Inject

class GetContinentsUseCase @Inject constructor(
    private val countriesRepository: IContriesRepository
) {
    suspend operator fun invoke() = countriesRepository.getContinents()
}
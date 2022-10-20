package com.example.countriesmvvm.presentation.country

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesmvvm.data.common.ClientResult
import com.example.countriesmvvm.data.common.DataSourceException
import com.example.countriesmvvm.domain.models.Country
import com.example.countriesmvvm.domain.usecases.GetCountryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {

    private val country = MutableLiveData<Country>()

    private val errorResult = MutableLiveData<DataSourceException>()

    private val progress = MutableLiveData(true)

    val countryLive
        get() = country

    val errorResultLive
        get() = errorResult

    val progressLive
        get() = progress

    fun getCountryByCode(code: String) {
        viewModelScope.launch {
            when (val result = getCountryUseCase.call(code)) {
                is ClientResult.Success -> {
                    progress.postValue(false)
                    country.postValue(result.data)
                }
                is ClientResult.Error -> {
                    progress.postValue(false)
                    errorResult.postValue(result.exception)
                }
                ClientResult.Loading -> {
                    progress.postValue(true)
                }
            }
        }
    }
}
package com.example.countriesmvvm.presentation.continents

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesmvvm.data.common.DataSourceException
import com.example.countriesmvvm.data.common.onError
import com.example.countriesmvvm.data.common.onLoading
import com.example.countriesmvvm.data.common.onSuccess
import com.example.countriesmvvm.domain.models.Continent
import com.example.countriesmvvm.domain.usecases.GetContinentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContinentsViewModel @Inject constructor(
    private val getContinentsUseCase: GetContinentsUseCase
) : ViewModel() {

    private val continentsList = MutableLiveData<List<Continent>>()

    private val errorResult = MutableLiveData<DataSourceException>()

    private val progress = MutableLiveData<Boolean>()

    val continentsListLive
        get() = continentsList

    val errorResultLive
        get() = errorResult

    val progressLive
        get() = progress

    fun getListOfContinents() {
        viewModelScope.launch {
            getContinentsUseCase().collect {
                it.onSuccess { continents ->
                    progress.postValue(false)
                    continentsList.postValue(continents)
                }
                    .onError { error ->
                        progress.postValue(false)
                        errorResult.postValue(error)
                    }
                    .onLoading {
                        progress.postValue(true)
                    }
            }
        }
    }
}
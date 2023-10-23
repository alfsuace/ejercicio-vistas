package com.alfsuace.ejerciciovistas.features.dog.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alfsuace.ejerciciovistas.app.ErrorApp
import com.alfsuace.ejerciciovistas.features.dog.domain.Dog
import com.alfsuace.ejerciciovistas.features.dog.domain.GetDogUseCase
import kotlinx.coroutines.Dispatchers

class DogViewModel(
    private val getDogUseCase: GetDogUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState



    fun loadDog() {
        getDogUseCase(Dispatchers.IO).fold(
            { responseError(it) },
            { responseGetDogSuccess(it) }
        )
    }

    private fun responseGetDogSuccess(it: Dog) {
        _uiState.postValue(UiState(dog = it))
    }

    private fun responseError(it: ErrorApp) {
        _uiState.postValue(UiState(errorApp = it))
    }


    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val dog: Dog? = null
    )
}

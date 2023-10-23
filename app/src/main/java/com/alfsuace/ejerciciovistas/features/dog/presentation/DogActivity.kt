package com.alfsuace.ejerciciovistas.features.dog.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.alfsuace.ejerciciovistas.R
import com.alfsuace.ejerciciovistas.app.ErrorApp
import com.alfsuace.ejerciciovistas.app.serialization.GsonSerialization
import com.alfsuace.ejerciciovistas.databinding.ActivityDogBinding
import com.alfsuace.ejerciciovistas.features.dog.data.DogDataRepository
import com.alfsuace.ejerciciovistas.features.dog.data.local.DogLocalDataSource
import com.alfsuace.ejerciciovistas.features.dog.data.remote.ApiDogRemoteDataSource
import com.alfsuace.ejerciciovistas.features.dog.domain.Dog
import com.alfsuace.ejerciciovistas.features.dog.domain.GetDogUseCase
import hide
import show

class DogActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDogBinding

    val viewModel: DogViewModel by lazy {
        DogViewModel(
            GetDogUseCase(
                DogDataRepository(
                    DogLocalDataSource(this, GsonSerialization()),
                    ApiDogRemoteDataSource()
                )
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewBinding()
        setupObserver()
        viewModel.loadDog()
    }
    private fun setupViewBinding() {
        binding = ActivityDogBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupObserver() {
        val observer = Observer<DogViewModel.UiState> { uiState ->
            uiState.dog?.apply {
                bindData(this)
            }
            uiState.errorApp?.let {
                showError(it)
            }
            if (uiState.isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
        }
        viewModel.uiState.observe(this, observer)
    }
    private fun bindData(dog: Dog) {
        binding.apply {
            dogName.text=dog.name
            dogDecription.text=dog.description
            dogBday.text=dog.dateBorn.toString()
            dogGengre.text=dog.genre


        }
    }
    private fun showError(error: ErrorApp) {
        binding.dog.hide()
        binding.viewError.layoutError.show()
        when (error) {
            ErrorApp.UnknowErrorApp -> binding.viewError.labelMesaggeError.text =
                getString(R.string.label_unknown_error)

            ErrorApp.DataErrorApp -> TODO()
            ErrorApp.InternetConectionErrorApp -> TODO()
        }
    }
    private fun showLoading() {
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideLoading() {
        binding.skeletonLayout.showOriginal()
    }
}
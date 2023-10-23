package com.alfsuace.ejerciciovistas.features.dog.domain


import com.alfsuace.ejerciciovistas.app.Either
import com.alfsuace.ejerciciovistas.app.ErrorApp
import kotlinx.coroutines.CoroutineDispatcher


class GetDogUseCase (private val repository: DogRepository){
    operator fun invoke(io: CoroutineDispatcher): Either<ErrorApp, Dog> {
        return repository.obtain()
    }
}
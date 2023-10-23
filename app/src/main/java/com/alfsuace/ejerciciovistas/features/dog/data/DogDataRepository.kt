package com.alfsuace.ejerciciovistas.features.dog.data


import com.alfsuace.ejerciciovistas.app.Either
import com.alfsuace.ejerciciovistas.app.ErrorApp
import com.alfsuace.ejerciciovistas.app.right
import com.alfsuace.ejerciciovistas.features.dog.data.local.DogLocalDataSource
import com.alfsuace.ejerciciovistas.features.dog.data.remote.ApiDogRemoteDataSource
import com.alfsuace.ejerciciovistas.features.dog.domain.Dog
import com.alfsuace.ejerciciovistas.features.dog.domain.DogRepository

class DogDataRepository(
    private val localDataSource: DogLocalDataSource,
    private val remoteDataSource: ApiDogRemoteDataSource
) : DogRepository {
    override fun obtain(): Either<ErrorApp, Dog> {
        val localDog = localDataSource.getDogs()
        return if (localDog.isRight() && localDog.getOrNull() != null) {
            localDog
        } else {
            val remoteDog = remoteDataSource.getDog()
            remoteDog.map {
                localDataSource.saveDog(it)
            }
            remoteDog
        }
    }
}

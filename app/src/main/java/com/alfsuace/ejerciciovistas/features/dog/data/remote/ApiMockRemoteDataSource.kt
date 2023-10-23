package com.alfsuace.ejerciciovistas.features.dog.data.remote


import com.alfsuace.ejerciciovistas.app.Either
import com.alfsuace.ejerciciovistas.app.ErrorApp
import com.alfsuace.ejerciciovistas.app.right
import com.alfsuace.ejerciciovistas.features.dog.domain.Dog

class ApiMockRemoteDataSource {

    fun getDogMock(): Either<ErrorApp, Dog> {
        return Dog(1, "Remi", "un diamante por pulir", "hembra", "1-01-2020").right()
    }

}

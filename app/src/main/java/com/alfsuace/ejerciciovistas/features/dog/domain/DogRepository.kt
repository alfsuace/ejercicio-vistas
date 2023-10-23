package com.alfsuace.ejerciciovistas.features.dog.domain


import com.alfsuace.ejerciciovistas.app.ErrorApp

import com.alfsuace.ejerciciovistas.app.Either



interface DogRepository {

    fun obtain(): Either<ErrorApp, Dog>
}
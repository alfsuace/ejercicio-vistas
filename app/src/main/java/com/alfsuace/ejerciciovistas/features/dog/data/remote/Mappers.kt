package com.alfsuace.ejerciciovistas.features.dog.data.remote

import com.alfsuace.ejerciciovistas.features.dog.domain.Dog

    fun DogApiModel.toModel(): Dog =
        Dog(1, this.name, this.description, this.genre, this.dateBorn)

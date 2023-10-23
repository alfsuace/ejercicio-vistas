package com.alfsuace.ejerciciovistas.features.dog.data.remote

import com.alfsuace.ejerciciovistas.features.dog.domain.Dog
import com.google.gson.annotations.SerializedName

data class DogApiModel (
    @SerializedName("name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("genre") val genre: String,
    @SerializedName("dateBorn") val dateBorn: String
    )

package com.alfsuace.ejerciciovistas.features.dog.domain

import java.util.Date

data class Dog(
    val id: Int,
    val name: String,
    val description: String,
    val genre: String,
    val dateBorn: String
)
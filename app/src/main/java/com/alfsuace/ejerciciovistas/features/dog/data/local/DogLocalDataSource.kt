package com.alfsuace.ejerciciovistas.features.dog.data.local

import android.content.Context
import com.alfsuace.ejerciciovistas.app.Either
import com.alfsuace.ejerciciovistas.app.ErrorApp
import com.alfsuace.ejerciciovistas.app.left
import com.alfsuace.ejerciciovistas.app.right
import com.alfsuace.ejerciciovistas.app.serialization.JsonSerialization

import com.alfsuace.ejerciciovistas.features.dog.domain.Dog
import com.google.gson.Gson

class DogLocalDataSource(
    private val context: Context,
    private val serialization: JsonSerialization
) {
    val sharedPref = context.getSharedPreferences("dogs", Context.MODE_PRIVATE)
    private val gson = Gson()
    private val dog_id = "1"

    fun getDogs(): Either<ErrorApp, Dog> {
        val jsonDog = sharedPref.getString(dog_id, null)
        jsonDog?.let {
            return serialization.fromJson(it, Dog::class.java).right()
        }
        return ErrorApp.UnknowErrorApp.left()
    }

    fun saveDog(input: Dog): Either<ErrorApp, Boolean> {
        return try {
            with(sharedPref.edit()) {
                val id = System.currentTimeMillis().toInt()
                val dog = Dog(
                    id,
                    input.name,
                    input.description,
                    input.genre,
                    input.dateBorn
                )
                val jsonDog = gson.toJson(
                    dog,
                    Dog::class.java
                )
                putString(id.toString(), jsonDog)
                apply()
            }
            true.right()
        } catch (ex: Exception) {
            ErrorApp.UnknowErrorApp.left()
        }
    }
}

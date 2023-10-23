package com.alfsuace.ejerciciovistas.features.dog.data.remote

import com.alfsuace.ejerciciovistas.app.Either
import com.alfsuace.ejerciciovistas.app.ErrorApp
import com.alfsuace.ejerciciovistas.app.left
import com.alfsuace.ejerciciovistas.app.right
import com.alfsuace.ejerciciovistas.features.dog.domain.Dog
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException


class ApiDogRemoteDataSource {

    private val baseUrl = "https://dam.sitehub.es/curso-2023-2024/api/huellas-view.json"

    fun getDog(): Either<ErrorApp, Dog> {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: DogApiService = retrofit.create(DogApiService::class.java)

      try {
          val repos: Response<DogApiModel> = service.getDog()

          return if (repos.isSuccessful) {
              repos.body()!!.toModel().right()
          } else {
              ErrorApp.InternetConectionErrorApp.left()
          }
      } catch (ex: TimeoutException){
          return ErrorApp.InternetConectionErrorApp.left()
      } catch (ex: UnknownHostException){
          return ErrorApp.InternetConectionErrorApp.left()
      } catch (ex: Exception){
          return ErrorApp.UnknowErrorApp.left()
      }
    }


}
package com.alfsuace.ejerciciovistas.features.dog.data.remote

import retrofit2.Response
import retrofit2.http.GET


interface DogApiService {
    //https://dam.sitehub.es/curso-2023-2024/api/huellas-view.json
    @GET("huellas-view.json")
    fun getDog(): Response<DogApiModel>
}
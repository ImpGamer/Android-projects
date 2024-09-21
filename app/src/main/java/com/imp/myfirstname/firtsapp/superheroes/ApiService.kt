package com.imp.myfirstname.firtsapp.superheroes

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("/api/aa9bcf2ffc6cb55a45746d9157419d1f/search/{name}")
    suspend fun getSuperHeroesByName(@Path("name") heroeName: String): Response<SuperHeroResponse>

    @GET("/api/aa9bcf2ffc6cb55a45746d9157419d1f/{id}")
    suspend fun getSuperHeroeByID(@Path("id") superHeroId:String): Response<SuperHeroDetailResponse>

    companion object {
        fun getRetrofit(): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://superheroapi.com/") // URL base de la API
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}

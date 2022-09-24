package com.alis.rickandmorty.network

import com.alis.rickandmorty.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NetworkService {
    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") id: Int): Response<Character>
}
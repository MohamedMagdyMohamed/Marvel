package com.example.marvel.data.characters.remote.service

import com.example.marvel.data.characters.model.CharacterDataWrapper
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApiService {

    @GET("v1/public/characters")
    suspend fun getCharactersList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("nameStartsWith") nameStartsWith: String?
    ): CharacterDataWrapper
}

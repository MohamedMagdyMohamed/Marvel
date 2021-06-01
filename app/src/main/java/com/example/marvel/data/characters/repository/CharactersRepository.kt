package com.example.marvel.data.characters.repository

import androidx.paging.PagingData
import com.example.marvel.data.characters.model.Character
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {

    fun getCharactersList(): Flow<PagingData<Character>>
}

package com.example.marvel.data.characters.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.marvel.data.characters.model.Character
import com.example.marvel.data.characters.remote.datasource.CharactersRemoteDataSource
import com.example.marvel.data.characters.remote.service.CharactersApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(private val charactersApiService: CharactersApiService) :
    CharactersRepository {

    override fun getCharactersList(nameQuery: String?): Flow<PagingData<Character>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                maxSize = MAX_SIZE,
                initialLoadSize = INITIAL_LOAD_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { CharactersRemoteDataSource(charactersApiService, nameQuery) }
        ).flow
    }

    companion object {
        private const val PAGE_SIZE = 25
        private const val INITIAL_LOAD_SIZE = PAGE_SIZE * 2
        private const val MAX_SIZE = (PAGE_SIZE + PAGE_SIZE * 3)
    }
}

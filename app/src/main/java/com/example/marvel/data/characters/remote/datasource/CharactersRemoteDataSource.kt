package com.example.marvel.data.characters.remote.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvel.data.characters.model.Character
import com.example.marvel.data.characters.remote.service.CharactersApiService
import retrofit2.HttpException
import java.io.IOException

class CharactersRemoteDataSource(
    private val charactersApiService: CharactersApiService,
    private val nameQuery: String? = null
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val position = params.key ?: CHARACTERS_STARTING_PAGE_INDEX

        return try {
            val response =
                charactersApiService.getCharactersList(position, params.loadSize, nameQuery)
            val characters = response.data.results

            LoadResult.Page(
                data = characters,
                prevKey = if (position == CHARACTERS_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (characters.isEmpty()) null else position + params.loadSize
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition
    }

    companion object {
        private const val CHARACTERS_STARTING_PAGE_INDEX = 0
    }
}

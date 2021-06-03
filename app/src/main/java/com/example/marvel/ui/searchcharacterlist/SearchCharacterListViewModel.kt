package com.example.marvel.ui.searchcharacterlist

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.marvel.data.characters.repository.CharactersRepository
import com.example.marvel.utils.AbsentLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchCharacterListViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
    state: SavedStateHandle
) : ViewModel() {

    private val _currentQuery = state.getLiveData<String?>(CURRENT_QUERY, null)

    val charactersListLiveData = _currentQuery.switchMap { queryString ->
        if (queryString.isNullOrEmpty()) {
            AbsentLiveData.create()
        } else {
            charactersRepository.getCharactersList(queryString).cachedIn(viewModelScope)
                .asLiveData()
        }
    }

    fun searchCharacters(query: String) {
        _currentQuery.value = query
    }

    companion object {
        private const val CURRENT_QUERY = "current_query"
    }
}

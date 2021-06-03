package com.example.marvel.ui.characterslist

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.marvel.data.characters.model.Character
import com.example.marvel.data.characters.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersListsViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository,
) : ViewModel() {

    private var _charactersListLiveData = liveData {
        emitSource(getCharactersList())
    }
    val charactersListLiveData = Transformations.map(_charactersListLiveData) { it }

    private fun getCharactersList(): LiveData<PagingData<Character>> {
        return charactersRepository.getCharactersList().cachedIn(viewModelScope).asLiveData()
    }
}

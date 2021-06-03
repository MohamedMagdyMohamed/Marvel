package com.example.marvel.ui.characterdetail

import androidx.lifecycle.ViewModel
import com.example.marvel.data.characters.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor() : ViewModel() {

    var character: Character? = null
}

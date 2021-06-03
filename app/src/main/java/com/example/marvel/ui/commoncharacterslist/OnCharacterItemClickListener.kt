package com.example.marvel.ui.commoncharacterslist

import com.example.marvel.data.characters.model.Character

interface OnCharacterItemClickListener {

    fun onCharacterItemClicked(character: Character)
}

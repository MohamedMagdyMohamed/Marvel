package com.example.marvel.ui.characterslist

import com.example.marvel.data.characters.model.Character

interface OnCharacterItemClickListener {

    fun onCharacterItemClicked(character: Character)
}

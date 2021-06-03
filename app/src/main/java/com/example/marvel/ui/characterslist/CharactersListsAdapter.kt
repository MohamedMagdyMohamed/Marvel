package com.example.marvel.ui.characterslist

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.marvel.data.characters.model.Character
import com.example.marvel.ui.commoncharacterslist.OnCharacterItemClickListener
import com.example.marvel.ui.commoncharacterslist.characterComparator

class CharactersListsAdapter(private val onCharacterItemClickListener: OnCharacterItemClickListener) :
    PagingDataAdapter<Character, CharactersListsViewHolder>(characterComparator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersListsViewHolder {
        return CharactersListsViewHolder.create(parent, onCharacterItemClickListener)
    }

    override fun onBindViewHolder(holder: CharactersListsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}

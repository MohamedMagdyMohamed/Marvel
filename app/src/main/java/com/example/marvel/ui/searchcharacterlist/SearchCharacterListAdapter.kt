package com.example.marvel.ui.searchcharacterlist

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.marvel.data.characters.model.Character
import com.example.marvel.ui.commoncharacterslist.OnCharacterItemClickListener
import com.example.marvel.ui.commoncharacterslist.characterComparator

class SearchCharacterListAdapter(private val onCharacterItemClickListener: OnCharacterItemClickListener) :
    PagingDataAdapter<Character, SearchCharacterListViewHolder>(characterComparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchCharacterListViewHolder {
        return SearchCharacterListViewHolder.create(parent, onCharacterItemClickListener)
    }

    override fun onBindViewHolder(holder: SearchCharacterListViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }
}

package com.example.marvel.ui.characterslist

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.marvel.data.characters.model.Character

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

    companion object {
        private val characterComparator = object : DiffUtil.ItemCallback<Character>() {
            override fun areItemsTheSame(oldItem: Character, newItem: Character) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Character, newItem: Character) =
                oldItem == newItem
        }
    }
}

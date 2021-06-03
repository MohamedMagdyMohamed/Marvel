package com.example.marvel.ui.characterslist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.marvel.databinding.ItemCharacterBinding
import com.example.marvel.ui.commoncharacterslist.BaseCharacterListViewHolder
import com.example.marvel.ui.commoncharacterslist.OnCharacterItemClickListener

class CharactersListsViewHolder(
    binding: ItemCharacterBinding,
    onCharacterItemClickListener: OnCharacterItemClickListener
) : BaseCharacterListViewHolder(binding, onCharacterItemClickListener) {

    companion object {
        fun create(
            parent: ViewGroup,
            onCharacterItemClickListener: OnCharacterItemClickListener
        ): CharactersListsViewHolder {
            val binding =
                ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return CharactersListsViewHolder(binding, onCharacterItemClickListener)
        }
    }
}

package com.example.marvel.ui.searchcharacterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.marvel.databinding.ItemSearchCharacterBinding
import com.example.marvel.ui.commoncharacterslist.BaseCharacterListViewHolder
import com.example.marvel.ui.commoncharacterslist.OnCharacterItemClickListener

class SearchCharacterListViewHolder(
    binding: ItemSearchCharacterBinding,
    onCharacterItemClickListener: OnCharacterItemClickListener
) : BaseCharacterListViewHolder(binding, onCharacterItemClickListener) {

    companion object {
        fun create(
            parent: ViewGroup,
            onCharacterItemClickListener: OnCharacterItemClickListener
        ): SearchCharacterListViewHolder {
            val binding =
                ItemSearchCharacterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return SearchCharacterListViewHolder(binding, onCharacterItemClickListener)
        }
    }
}

package com.example.marvel.ui.commoncharacterslist

import androidx.recyclerview.widget.DiffUtil
import com.example.marvel.data.characters.model.Character

val characterComparator = object : DiffUtil.ItemCallback<Character>() {
    override fun areItemsTheSame(oldItem: Character, newItem: Character) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Character, newItem: Character) =
        oldItem == newItem
}

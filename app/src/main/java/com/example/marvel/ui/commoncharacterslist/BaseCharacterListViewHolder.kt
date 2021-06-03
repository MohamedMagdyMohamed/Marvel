package com.example.marvel.ui.commoncharacterslist

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.BR
import com.example.marvel.data.characters.model.Character

abstract class BaseCharacterListViewHolder(
    private val binding: ViewDataBinding,
    private val onCharacterItemClickListener: OnCharacterItemClickListener
) : RecyclerView.ViewHolder(binding.root) {

    private var currentItem: Character? = null

    init {
        binding.root.setOnClickListener {
            onCharacterItemClicked()
        }
    }

    private fun onCharacterItemClicked() {
        if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
            currentItem?.let { character ->
                onCharacterItemClickListener.onCharacterItemClicked(character)
            }
        }
    }

    fun bind(currentItem: Character) {
        this.currentItem = currentItem

        binding.setVariable(BR.character, currentItem)
        binding.executePendingBindings()
    }
}

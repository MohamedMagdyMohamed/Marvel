package com.example.marvel.ui.characterslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.BR
import com.example.marvel.data.characters.model.Character
import com.example.marvel.databinding.ItemCharacterBinding

class CharactersListsViewHolder(
    private val binding: ItemCharacterBinding,
    private val onCharacterItemClickListener: OnCharacterItemClickListener
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private var currentItem: Character? = null

    init {
        binding.root.setOnClickListener(this)
    }

    fun bind(currentItem: Character) {
        this.currentItem = currentItem

        binding.setVariable(BR.character, currentItem)
        binding.executePendingBindings()
    }

    override fun onClick(v: View?) {
        if (v == binding.root) {
            if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                currentItem?.let { character ->
                    onCharacterItemClickListener.onCharacterItemClicked(character)
                }
            }
        }
    }

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

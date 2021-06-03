package com.example.marvel.ui.characterslist

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class CharactersListLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<CharactersListLoadStateViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): CharactersListLoadStateViewHolder {
        return CharactersListLoadStateViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(holder: CharactersListLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}

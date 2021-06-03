package com.example.marvel.ui.characterslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.databinding.ItemCharacterStateHeaderFooterBinding

class CharactersListLoadStateViewHolder(
    private val binding: ItemCharacterStateHeaderFooterBinding,
    private val retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    init {
        binding.btnRetry.setOnClickListener(this)
    }

    fun bind(loadState: LoadState) {
        handleLoadingState(loadState)
        handleErrorState(loadState)
    }

    private fun handleLoadingState(loadState: LoadState) {
        binding.pbLoading.isVisible = loadState is LoadState.Loading
    }

    private fun handleErrorState(loadState: LoadState) {
        binding.apply {
            btnRetry.isVisible = loadState !is LoadState.Loading
            tvError.isVisible = loadState !is LoadState.Loading
            if (loadState is LoadState.Error) {
                tvError.text = loadState.error.localizedMessage
            }
        }
    }

    override fun onClick(v: View?) {
        if (v == binding.btnRetry) {
            retry.invoke()
        }
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): CharactersListLoadStateViewHolder {
            val binding = ItemCharacterStateHeaderFooterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return CharactersListLoadStateViewHolder(binding, retry)
        }
    }
}

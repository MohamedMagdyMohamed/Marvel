package com.example.marvel.ui.commoncharacterslist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvel.R
import com.example.marvel.data.characters.model.Character
import com.example.marvel.databinding.FragmentCommonCharactersListBinding
import com.example.marvel.ui.base.BaseFragment
import com.example.marvel.ui.characterslist.CharactersListsAdapter
import com.example.marvel.ui.characterslist.OnCharacterItemClickListener
import com.example.marvel.utils.MarginItemDecoration
import com.example.marvel.utils.UiUtils
import com.example.marvel.utils.autoCleared

open class BaseCharactersListFragment : BaseFragment(R.layout.fragment_common_characters_list),
    OnCharacterItemClickListener, View.OnClickListener {

    protected var binding by autoCleared<FragmentCommonCharactersListBinding>()
    private var adapter by autoCleared<CharactersListsAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCommonCharactersListBinding.bind(view)
        initUi()
    }

    private fun initUi() {
        setupAdapter()
        setupRecycleView()
        binding.btnRetry.setOnClickListener(this)
    }

    private fun setupAdapter() {
        adapter = CharactersListsAdapter(this)
        adapter.addLoadStateListener { loadState ->
            onLoadStateChanged(loadState)
        }
    }

    private fun setupRecycleView() {
        binding.apply {
            rvCharacters.setHasFixedSize(true)
            rvCharacters.adapter = adapter.withLoadStateHeaderAndFooter(
                header = CharactersListLoadStateAdapter { retry() },
                footer = CharactersListLoadStateAdapter { retry() }
            )
            rvCharacters.layoutManager = LinearLayoutManager(requireContext())
            rvCharacters.addItemDecoration(
                MarginItemDecoration(
                    UiUtils.getDimen(
                        requireContext(),
                        R.dimen.recycle_view_margin_16dp
                    ).toInt()
                )
            )
        }
    }

    private fun onLoadStateChanged(loadState: CombinedLoadStates) {
        handleLoadingState(loadState)
        handleErrorState(loadState)
        handleEmptyState(loadState)
    }

    private fun handleLoadingState(loadState: CombinedLoadStates) {
        binding.pbLoading.isVisible = loadState.source.refresh is LoadState.Loading
    }

    private fun handleErrorState(loadState: CombinedLoadStates) {
        val errorState = loadState.source.append as? LoadState.Error
            ?: loadState.source.prepend as? LoadState.Error
            ?: loadState.append as? LoadState.Error
            ?: loadState.prepend as? LoadState.Error
            ?: loadState.refresh as? LoadState.Error

        binding.apply {
            tvError.text = errorState?.error?.localizedMessage
            btnRetry.isVisible = loadState.source.refresh is LoadState.Error
            tvError.isVisible = loadState.source.refresh is LoadState.Error
        }
    }

    private fun handleEmptyState(loadState: CombinedLoadStates) {
        binding.tvEmpty.isVisible = loadState.source.refresh is LoadState.NotLoading &&
            loadState.append.endOfPaginationReached &&
            adapter.itemCount < 1
    }

    fun submitList(list: PagingData<Character>) {
        adapter.submitData(viewLifecycleOwner.lifecycle, list)
    }

    override fun onCharacterItemClicked(character: Character) {}

    override fun onClick(v: View?) {
        if (v == binding.btnRetry) {
            retry()
        }
    }

    private fun retry() {
        adapter.retry()
    }
}

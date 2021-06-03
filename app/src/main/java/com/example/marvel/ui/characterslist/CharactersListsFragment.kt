package com.example.marvel.ui.characterslist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import com.example.marvel.R
import com.example.marvel.data.characters.model.Character
import com.example.marvel.ui.commoncharacterslist.BaseCharactersListFragment
import com.example.marvel.ui.commoncharacterslist.CharactersListLoadStateAdapter
import com.example.marvel.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListsFragment : BaseCharactersListFragment() {

    private val viewModel: CharactersListsViewModel by viewModels()
    private var adapter by autoCleared<CharactersListsAdapter>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun setupAdapter() {
        adapter = CharactersListsAdapter(this)
        adapter.addLoadStateListener { loadState ->
            onLoadStateChanged(loadState)
        }
    }

    override fun getConcatAdapter(): ConcatAdapter {
        return adapter.withLoadStateHeaderAndFooter(
            header = CharactersListLoadStateAdapter { retry() },
            footer = CharactersListLoadStateAdapter { retry() }
        )
    }

    override fun retry() {
        adapter.retry()
    }

    override fun getNavDirection(character: Character): NavDirections {
        return CharactersListsFragmentDirections.actionHomeFragmentToCharacterDetailFragment(
            character
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_characters_list, menu)

        val searchItem = menu.findItem(R.id.action_search)
        searchItem.setOnMenuItemClickListener {
            replaceFragment(R.id.action_homeFragment_to_searchCharacterListFragment)
            return@setOnMenuItemClickListener true
        }
    }

    override fun observeData() {
        viewModel.charactersListLiveData.observe(viewLifecycleOwner) {
            submitList(it)
        }
    }

    private fun submitList(list: PagingData<Character>) {
        adapter.submitData(viewLifecycleOwner.lifecycle, list)
    }

    override fun isAdapterEmpty(): Boolean {
        return adapter.itemCount == 0
    }
}

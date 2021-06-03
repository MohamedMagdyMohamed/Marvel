package com.example.marvel.ui.searchcharacterlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
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
class SearchCharacterListFragment :
    BaseCharactersListFragment(), MenuItem.OnActionExpandListener, SearchView.OnQueryTextListener {

    private val viewModel: SearchCharacterListViewModel by viewModels()
    private var searchView by autoCleared<SearchView>()
    private var adapter by autoCleared<SearchCharacterListAdapter>()

    override fun observeData() {
        viewModel.charactersListLiveData.observe(viewLifecycleOwner) { pagingList ->
            pagingList?.let {
                submitList(it)
            }
        }
    }

    private fun submitList(list: PagingData<Character>) {
        adapter.submitData(viewLifecycleOwner.lifecycle, list)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
    }

    override fun setupAdapter() {
        adapter = SearchCharacterListAdapter(this)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.menu_search_characters_list, menu)

        val searchItem = menu.findItem(R.id.action_search)
        searchItem.expandActionView()
        searchItem.setOnActionExpandListener(this)

        searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
    }

    /**
     * Called when SearchView is expanding
     */
    override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
        return true
    }

    /**
     * Called when SearchView is collapsing
     */
    override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
        goBack()
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchView.clearFocus()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            submitList(PagingData.empty())
            binding.rvCharacters.scrollToPosition(0)
            viewModel.searchCharacters(newText)
        }
        return true
    }

    override fun isAdapterEmpty(): Boolean {
        return adapter.itemCount == 0
    }

    override fun getNavDirection(character: Character): NavDirections {
        searchView.clearFocus()
        return SearchCharacterListFragmentDirections.actionSearchCharacterListFragmentToCharacterDetailFragment(
            character
        )
    }
}

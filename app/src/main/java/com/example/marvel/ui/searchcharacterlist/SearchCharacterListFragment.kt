package com.example.marvel.ui.searchcharacterlist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.paging.PagingData
import com.example.marvel.R
import com.example.marvel.ui.commoncharacterslist.BaseCharactersListFragment
import com.example.marvel.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchCharacterListFragment : BaseCharactersListFragment(), MenuItem.OnActionExpandListener,
    SearchView.OnQueryTextListener {

    private val viewModel: SearchCharacterListViewModel by viewModels()
    private var searchView by autoCleared<SearchView>()

    override fun observeData() {
        viewModel.charactersListLiveData.observe(viewLifecycleOwner) { pagingList ->
            pagingList?.let {
                submitList(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
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
}

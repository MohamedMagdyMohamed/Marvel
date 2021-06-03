package com.example.marvel.ui.characterslist

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.example.marvel.R
import com.example.marvel.ui.commoncharacterslist.BaseCharactersListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharactersListsFragment : BaseCharactersListFragment() {

    private val viewModel: CharactersListsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
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
}

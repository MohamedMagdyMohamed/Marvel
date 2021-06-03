package com.example.marvel.ui.characterdetail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.marvel.BR
import com.example.marvel.R
import com.example.marvel.databinding.FragmentCharacterDetailBinding
import com.example.marvel.ui.base.BaseFragment
import com.example.marvel.utils.autoCleared
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailFragment : BaseFragment(R.layout.fragment_character_detail) {

    private var binding by autoCleared<FragmentCharacterDetailBinding>()
    private val viewModel: CharacterDetailViewModel by viewModels()
    private val params by navArgs<CharacterDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.character = params.character
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentCharacterDetailBinding.bind(view)
        initUi()
    }

    private fun initUi() {
        binding.apply {
            setupToolbar(toolbar)
            setVariable(BR.character, viewModel.character)
            executePendingBindings()
        }
    }
}

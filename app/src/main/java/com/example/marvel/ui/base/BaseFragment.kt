package com.example.marvel.ui.base

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import com.example.marvel.ui.activities.MainActivity

abstract class BaseFragment(@LayoutRes contentLayoutId: Int) : Fragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()
    }

    /**
     * Extend to observe livedata.
     */
    open fun observeData() {}

    private fun getMainActivity() = activity as MainActivity?

    fun replaceFragment(action: Int) {
        getMainActivity()?.replaceFragment(action)
    }

    fun replaceFragment(direction: NavDirections) {
        getMainActivity()?.replaceFragment(direction)
    }

    fun goBack() {
        getMainActivity()?.onBackPressed()
    }

    fun setupToolbar(toolbar: Toolbar) {
        getMainActivity()?.setupToolbar(toolbar)
    }
}
